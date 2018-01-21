from importance import importance, rand
from plurality import plurality
from graph import Tree
from copy import copy
import collections

# The function IMPORTANCE calculates the information gain of an attribute
IMPORTANCE = rand

class Tree:
    def __init__(self, root, left=None, right=None):
        self.root = root
        self.left = left
        self.right = right
    
    def __str__(self):
        return "[" + str(self.root) + "]"

    def add_branch_left(self, tree):
        self.left = tree

    def add_branch_right(self, tree):
        self.right = tree
        

def decision_tree_learning(examples, attrs, parent_examples):
    # Check and classify training data
    # Create new decision tree

    classes = [example[7] for example in examples]
    same_classification = all(x is classes[0] for x in classes)

    if not examples:
        # leaf node reached
        # classify based on what's the most commong class of parent examples
        return plurality_value(parent_examples)
    elif same_classification:
        # all remaining examples have the same class
        return classes[0]
    elif not attrs:
        # remaining examples have different classes
        # classify based on what's the most common class of the examples
        return plurality_value(examples)
    else:
        # calculate the information gain of choosing the different attrs
        importance = [IMPORTANCE(attr, examples) for attr in attrs]

        # get index of the most important attribute
        i = importance.index(max(importance))

        # pop the most important attribute
        a = attrs.pop(i)

        print("chose attribute: " + str(a))

        # generate new tree with most important attr as root
        tree = Tree(a)

        
        for val in [1,2]:
            # get examples that are relevant for the current branch
            exs = [e for e in examples if e[a] is val]

            # recursive call
            subtree = decision_tree_learning(exs, attrs, examples)

            if val is 1:
                tree.add_branch_left(subtree)
            if val is 2:
                tree.add_branch_right(subtree)

        return tree


def plurality_value(examples):
    """
    Selects the most common output value among a set of examples, breaking
    ties randomly. Plurality is the generalization of majority to more than
    2 classes
    """

    numb_pos = 0
    numb_neg = 0

    for example in examples:
        if example[7] is 2: numb_pos += 1
        if example[7] is 1: numb_neg += 1

    return 2 if numb_pos >= numb_neg else 1


def train():
    pass


def test(tree, data):

    results = []

    for obj in data:
        current = tree

        while True:

            if type(current) is int:
                results.append(current)
                break

            attr = current.root
            val = obj[attr]

            if val is 1:
                current = current.left
            if val is 2:
                current = current.right


    answers = [obj[7] for obj in data]

    errors = 0

    for i, result in enumerate(results):
        if result is not answers[i]:
            errors += 1

    return 1 - float(errors)/len(results)


def print_preorder(tree, depth=0):
    tab = "-->"
    print(tab * depth + str(tree))

    if type(tree) is not int:
        print_preorder(tree.left, depth+1)
        print_preorder(tree.right, depth+1)


def main():
    # Read training data
    f = open("training.txt")

    attrs = [
            0,  # attr1
            1,  # attr2
            2,  # attr3
            3,  # attr4
            4,  # attr5
            5,  # attr6
            6   # attr7
        ]

    examples = []

    for i, line in enumerate(f):
        # A line looks like this:
        #   1   1   2   2   1   1   1   1
        # which means that
        #   attr1 = 1
        #   attr2 = 1
        #   attr3 = 1
        #   attr4 = 1
        #   attr5 = 1
        #   attr6 = 1
        #   attr7 = 1
        #   class = 1
        examples.append([int(numb) for numb in line.strip().split('\t')])


    parent_examples = []

    print('\n')
    print("CREATING TREE")
    # Run learning algorithm
    tree = decision_tree_learning(examples, attrs, parent_examples)

    print('\n')
    print("DECISION TREE (PRE-ORDER)")
    print_preorder(tree)

    # Read test data
    test_data = []

    f = open("test.txt")

    for i, line in enumerate(f):
        test_data.append([int(numb) for numb in line.strip().split('\t')])


    print('\n')
    print("TESTING")

    # Run test
    accuracy = test(tree, test_data)
    print("accuracy: " + str(round(accuracy,2)))



if __name__ == '__main__':
    print("Decision Tree Learning")
    main()
