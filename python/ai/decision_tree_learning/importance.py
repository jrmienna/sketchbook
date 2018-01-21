from math import log
from random import random

def H(*args):
    """Entropy function"""
    sum = 0
    for arg in args:
        if arg > 0:
            sum -= arg * log(arg, 2)
    return sum

def importance(a, examples):
    """ Calculates the information gain of an attribute"""

    c = len(examples[0]) - 1

    attr = [0.0, 0.0]   # [pos, neg] of attribute

    nodes = [
            [0.0, 0.0],  # [pos, neg] of first branch
            [0.0 ,0.0]   # [pos, neg] of second branch
            ]

    for example in examples:

        # subract 1 to adjust for zero-based index
        val     = example[a] - 1
        _class  = example[c] - 1 

        # count positives and negatives in total
        attr[_class] += 1

        # count positives and negatives at nodes
        nodes[val][_class] += 1

    total = len(examples)
    entropy = H(attr[0]/total, attr[1]/total)

    expected_entropy = 0

    for node in nodes:
        pos = node[0]
        neg = node[1]

        if not pos+neg > 0: continue

        prob = (pos+neg)/total
        utility = H(pos/(pos+neg), neg/(pos+neg))

        expected_entropy += prob * utility

    return entropy - expected_entropy


def rand(a, examples):
    """ Assigns random importance to attribute"""

    return random()

if __name__ == '__main__':
    attributes = [
            0,
            1,
            2
        ]
    examples = [
            [1,1,1,1],
            [1,1,1,1],
            [1,1,2,2],
            [1,2,2,2],
            [2,1,1,1],
            [2,2,2,2],
        ]

    a = 0
    assert round(importance(a,examples),2) == 0.0

    a = 1
    assert round(importance(a,examples),2) == 0.46

    a = 2
    assert round(importance(a,examples),2) == 1.0
    
