from pybrain.structure import FeedForwardNetwork, LinearLayer, TanhLayer, FullConnection
from pybrain.supervised.trainers import BackpropTrainer
from pybrain.datasets import SupervisedDataSet
from pybrain.tests.helpers import gradientCheck


inLayer = LinearLayer(1)
hiddenLayer = TanhLayer(8)
outLayer = LinearLayer(1)
in_to_hidden = FullConnection(inLayer, hiddenLayer)
hidden_to_out = FullConnection(hiddenLayer, outLayer)

n = FeedForwardNetwork()
n.addInputModule(inLayer)
n.addModule(hiddenLayer)
n.addOutputModule(outLayer)
n.addConnection(in_to_hidden)
n.addConnection(hidden_to_out)
n.sortModules()

ds = SupervisedDataSet(1, 1)
ds.addSample((1.2,), (2.2,))
ds.addSample((2.1,), (10.2,))
ds.addSample((3.5,), (-1.2,))
ds.addSample((4.1,), (-12.2,))
ds.addSample((5.1,), (2.9,))
ds.addSample((-6.9,), (6.2,))
ds.addSample((-7.2,), (1.0,))
ds.addSample((8.1,), (0.1,))

trainer = BackpropTrainer(n, ds)
trainer.trainUntilConvergence(verbose=False,
                              validationProportion = 0.15,
                              maxEpochs = 1000,
                              continueEpochs = 10)

errors = []
for i in range(1,8):
    out = n.activate((i,))[0]
    print out
    error = i - out
    errors.append(error**2)

print errors
print sum(errors)
