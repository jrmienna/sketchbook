# 1. Initialisation
# 2. Activation
# 3. Weight training
# 4. Iteration

import random
from matplotlib import pyplot


class Perceptron:
    def __init__(self, n, threshold, trainingRate):
        self.n = n
        self.a = trainingRate
        self.threshold = threshold
        self.weights = [round(random.uniform(-0.5, 0.5), 1) for i in range(n)]

    def response(self, inputs):
        '''Output for given inputs'''

        n = self.n
        s = 0
        for i in range(n):
            s += inputs[i] * self.weights[i]

        diff = s - self.threshold

        return 1 if diff >= 0 else 0

    def train(self, inputs, error):
        '''Updates the weights of the perceptron'''

        n = self.n
        for i in range(n):
            delta_w = self.a * inputs[i] * error
            self.weights[i] = round(self.weights[i] + delta_w, 1)


def plot(outputs):
    pass


def generate_data(n):
    return [random.randint(0, 1) for i in range(n)]


def hasConverged(perceptron, data):
    p = perceptron

    for row in data:
       inputs = [row[0], row[1]]
       y = row[2]
       if p.response(inputs) is not y: return False

    return True

def train(perceptron, data):
    sum_errors = []
    sum_error = 0
    epochs = 0

    while True:
        print perceptron.weights

        sum_error = 0

        for row in data:
            inputs = [row[0], row[1]]
            y = row[2]
            response = perceptron.response(inputs)

            error = y - response
            sum_error += error**2

            if abs(error) > 0:
                perceptron.train(inputs, error)

        sum_errors.append(sum_error)
        epochs += 1

        if sum_error == 0:
            break

    pyplot.plot(sum_errors)
    pyplot.xlabel("Epoch")
    pyplot.ylabel("Sum-squared error")
    pyplot.axis([0, 6, 0, 6])
    pyplot.show()
    print "Epochs: " + str(epochs)


def main():
    and_data = [
        [0, 0, 0],
        [0, 1, 0],
        [1, 0, 0],
        [1, 1, 1]
    ]

    or_data = [
        [0, 0, 0],
        [0, 1, 1],
        [1, 0, 1],
        [1, 1, 1]
    ]

    p = Perceptron(2, 0.2, 0.1)

    train(p, or_data)

    print p.response([0, 0])
    print p.response([0, 1])
    print p.response([1, 0])
    print p.response([1, 1])

if __name__ == '__main__':
    main()
