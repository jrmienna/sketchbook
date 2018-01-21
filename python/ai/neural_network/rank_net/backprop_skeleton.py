import math
import random
import copy

#The transfer function of neurons, g(x)
def sigmoid(x):
    return (1.0/(1.0+math.exp(-x)))

#The derivative of the transfer function, g'(x)
def sigmoid_prime(x):
    return math.exp(-x)/(pow(math.exp(-x)+1,2))

def random_float(low,high):
    return random.random()*(high-low) + low

#Initializes a matrix of all zeros
def make_matrix(I, J):
    m = []
    for i in range(I):
        m.append([0]*J)
    return m

class NN: #Neural Network
    def __init__(self, num_inputs, num_hidden, learning_rate=0.001):
        #Inputs: number of input and hidden nodes. Assuming a single output node.
        # +1 for bias node: A node with a constant input of 1. Used to shift the transfer function.
        self.num_inputs = num_inputs + 1
        self.num_hidden = num_hidden

        # Current activation levels for nodes (in other words, the nodes' output value)
        self.input_activation = [1.0]*self.num_inputs
        self.hidden_activations = [1.0]*self.num_hidden
        self.output_activation = 1.0 #Assuming a single output.
        self.learning_rate = learning_rate

        # create weights
        #A matrix with all weights from input layer to hidden layer
        self.weights_input = make_matrix(self.num_inputs,self.num_hidden)
        #A list with all weights from hidden layer to the single output neuron.
        self.weights_output = [0 for i in range(self.num_hidden)]# Assuming single output
        # set them to random vaules
        for i in range(self.num_inputs):
            for j in range(self.num_hidden):
                self.weights_input[i][j] = random_float(-0.5, 0.5)
        for j in range(self.num_hidden):
            self.weights_output[j] = random_float(-0.5, 0.5)

        #Data for the backpropagation step in RankNets.
        #For storing the previous activation levels (output levels) of all neurons
        self.prev_input_activations = []
        self.prev_hidden_activations = []
        self.prev_output_activation = 0
        #For storing the previous delta in the output and hidden layer
        self.prev_delta_output = 0
        self.prev_delta_hidden = [0 for i in range(self.num_hidden)]
        #For storing the current delta in the same layers
        self.delta_output = 0
        self.delta_hidden = [0 for i in range(self.num_hidden)]

    def propagate(self, inputs):
        if len(inputs) != self.num_inputs-1:
            raise ValueError('wrong number of inputs')

        # input activations
        self.prev_input_activations=copy.deepcopy(self.input_activation)
        for i in range(self.num_inputs-1):
            self.input_activation[i] = inputs[i]
        self.input_activation[-1] = 1 #Set bias node to -1.

        # hidden activations
        self.prev_hidden_activations=copy.deepcopy(self.hidden_activations)
        for j in range(self.num_hidden):
            sum = 0.0
            for i in range(self.num_inputs):
                #print self.ai[i] ," * " , self.wi[i][j]
                sum = sum + self.input_activation[i] * self.weights_input[i][j]
            self.hidden_activations[j] = sigmoid(sum)

        # output activations
        self.prev_output_activation=self.output_activation
        sum = 0.0
        for j in range(self.num_hidden):
            sum = sum + self.hidden_activations[j] * self.weights_output[j]
        self.output_activation = sigmoid(sum)
        return self.output_activation

    def compute_output_delta(self):
        output_a = self.prev_output_activation
        output_b = self.output_activation

        # probability of a being ranked higher than b
        prob = sigmoid(output_a - output_b)

        delta_a = sigmoid_prime(output_a) * (1-prob)
        delta_b = sigmoid_prime(output_b) * (1-prob)

        self.prev_delta_output = delta_a
        self.delta_output = delta_b

    def compute_hidden_delta(self):
        for i in reversed(xrange(self.num_hidden)):
            out_a = self.prev_hidden_activations[i]
            out_b = self.hidden_activations[i]

            prob = sigmoid(out_a - out_b)

            doa = sigmoid_prime(out_a) * (1-prob)   # delta output a
            dob = sigmoid_prime(out_b) * (1-prob)   # delta output b

            weight = self.weights_output[i]

            dha = sigmoid_prime(out_a) * weight * (doa - dob)  # delta hidden a
            dhb = sigmoid_prime(out_b) * weight * (doa - dob)  # delta hidden b
            
            self.prev_delta_hidden[i] = dha
            self.delta_hidden[i] = dha

    def update_weights(self):
        # Update self.weights_input using
        # prev_delta_hidden and delta_hidden
        for i in reversed(xrange(self.num_inputs)):
            for j in reversed(xrange(self.num_hidden)):
                out_a = self.prev_hidden_activations[j]
                out_b = self.hidden_activations[j]
                delta_a = self.prev_delta_hidden[j]
                delta_b = self.delta_hidden[j]

                self.weights_input[i][j] += self.learning_rate * (delta_a*out_a - delta_b*out_b)

        # Update self.weights_output using
        # prev_delta_output and delta_output
        for weight in self.weights_output:
            out_a = self.prev_output_activation
            out_b = self.output_activation
            delta_a = self.prev_delta_output
            delta_b = self.delta_output

            weight += self.learning_rate * (delta_a*out_a - delta_b*out_b)



    def backpropagate(self):
        self.compute_output_delta()
        self.compute_hidden_delta()
        self.update_weights()

    #Prints the network weights
    def weights(self):
        print('Input weights:')
        for i in range(self.num_inputs):
            print(self.weights_input[i])
        print()
        print('Output weights:')
        print(self.weights_output)

    def train(self, patterns, iterations=1):
        # TODO: Train the network on all patterns for a number of iterations.
        # To measure performance each iteration: Run for 1 iteration, then count
        # misordered pairs.

        error_rates = []

        for i in xrange(iterations):
            for pattern in patterns:
                a = pattern[0]
                b = pattern[1]

                #TODO: Training is done like this (details in exercise text):
                #-Propagate A
                self.propagate(a)
                #-Propagate B
                self.propagate(b)
                #-Backpropagate
                self.backpropagate()

            error_rates.append(self.count_misordered_pairs(patterns))

        return error_rates


    def count_misordered_pairs(self, patterns):
        # TODO: Let the network classify all pairs of patterns.
        # The highest output determines the winner.

        num_right = 0.0
        num_misses = 0.0

        for pattern in patterns:
            a = pattern[0]
            b = pattern[1]

            #Propagate A
            self.propagate(a)
            #Propagate B
            self.propagate(b)

            if self.prev_output_activation > self.output_activation:
                # a is winner
                num_right += 1
            else:
                # b is winner
                num_misses += 1

        return num_misses/(num_right+num_misses)
