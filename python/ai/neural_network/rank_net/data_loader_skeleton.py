import backprop_skeleton as bp
import matplotlib.pyplot as plt

#Class for holding your data - one object for each line in the dataset
class data_instance:

    def __init__(self,qid,rating,features):
        self.qid = qid #ID of the query
        self.rating = rating #Rating of this site for this query
        self.features = features #The features of this query-site pair.

    def __str__(self):
        return "Datainstance - qid: "+ str(self.qid)+ ". rating: "+ str(self.rating)+ ". features: "+ str(self.features)


#A class that holds all the data in one of our sets (the training set or the
#testset)
class DataHolder:

    def __init__(self, dataset):
        self.dataset = self.load_data(dataset)

    def load_data(self,file):
        #Input: A file with the data.
        #Output: A dict mapping each query ID to the relevant documents, like
        #this: dataset[queryID] = [dataInstance1, dataInstance2, ...]
        data = open(file)
        dataset = {}
        for line in data:
            #Extracting all the useful info from the line of data
            line_data = line.split()
            rating = int(line_data[0])
            qid = int(line_data[1].split(':')[1])
            features = []
            for elem in line_data[2:]:
                if '#docid' in elem: #We reached a comment. Line done.
                    break
                features.append(float(elem.split(':')[1]))
            #Creating a new data instance, inserting in the dict.
            di = data_instance(qid,rating,features)
            if qid in dataset.keys():
                dataset[qid].append(di)
            else:
                dataset[qid]=[di]
        return dataset


def run_ranker(trainingset, testset):
    #Dataholders for training and testset
    dh_training = DataHolder(trainingset)
    dh_testing = DataHolder(testset)

    #Creating an ANN instance - feel free to experiment with the learning rate
    nn = bp.NN(46,10,0.01)

    #format: [(data1Features,data2Features),...,(data_nFeatures,data_mFeatures)]
    training_patterns = []  # The training patterns we will feed the network
    test_patterns = []      # The test patterns we will feed the network

    print("processing training data")

    for qid in dh_training.dataset.keys():

        # Get sites matching current query
        sites = dh_training.dataset[qid]
        
        # sort sites by ranking
        sites = sorted(sites, key=lambda i: i.rating, reverse=True)

        for i, site in enumerate(sites):
            other_sites = sites[i+1:]
            for other in other_sites:
                if site.rating is other.rating:
                    continue
                # Store features of differently ranked sites
                training_patterns.append((site.features,other.features))
                #print("("+str(site.rating)+","+str(other.rating)+")")


    print("processing test data")

    for qid in dh_testing.dataset.keys():

        sites = dh_testing.dataset[qid]

        #TODO: Store the test instances into the test_patterns array, once again as pairs.
        #TODO: Hint: The testing will be easier for you if you also now order the pairs - it will make it easy to see if the ANN agrees with your ordering.

        sites = sorted(sites, key=lambda i: i.rating, reverse=True)

        for i, site in enumerate(sites):
            other_sites = sites[i+1:]
            for other in other_sites:
                test_patterns.append((site.features,other.features))



    training_errors = []
    testing_errors = []

    #Check ANN performance before training
    print("checking performance")
    testing_errors.append(nn.count_misordered_pairs(test_patterns))
    epochs = 20 
    for i in range(epochs-1):
        print("##########################")
        print(str(epochs-1-i) + " epochs remaining")
        #Running 25 iterations, measuring testing performance after each round of training.
        #Training
        print("training")
        training_errors.append([error for error in nn.train(training_patterns,iterations=5)])
        #Check ANN performance after training.
        print("checking performance")
        testing_errors.append(nn.count_misordered_pairs(test_patterns))

    # TODO: Store the data returned by count_misordered_pairs and plot it,
    # showing how training and testing errors develop.

    plt.figure()
    plt.plot(training_errors)
    plt.plot(testing_errors)
    plt.grid(1)
    plt.ylabel('error')
    plt.xlabel('epoch')
    plt.show()

    nn.weights()



run_ranker("train.txt","test.txt")
