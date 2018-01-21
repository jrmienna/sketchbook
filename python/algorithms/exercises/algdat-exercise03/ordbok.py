from sys import stdin, stderr
import traceback

class Node:
    def __init__(self):
        self.child = {}
        self.posi = []

def build(wordlist):
    topNode = Node()
    for (word, position) in wordlist:
        node = topNode
        for letter in word:
            if not letter in node.child:
                node.child[letter] = Node()
            node = node.child[letter]
        node.posi.append(position)
    return topNode

def positions(word, index, node):
    if index >= len(word):
        posi = node.posi
    elif word[index] == "?":
        posi = []
        for child in node.child.values():
            posi += positions(word, index + 1, child)
    elif word[index] in node.child:
        posi = positions(word, index + 1, node.child[word[index]])
    else:
        posi = []
    return posi


try:
    word = stdin.readline().split()
    wordlist = []
    pos = 0
    for o in word:
        wordlist.append( (o,pos) )
        pos += len(o) + 1
    topnode = build(wordlist)
    for searchword in stdin:
        searchword = searchword.strip()
        print searchword + ":",
        posi = positions(searchword, 0, topnode)
        posi.sort()
        for p in posi:
            print p,
        print
except:
    traceback.print_exc(file=stderr)
