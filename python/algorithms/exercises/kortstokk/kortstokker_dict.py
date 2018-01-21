from sys import stdin
from random import randint

def insertionsort(a):
    n = len(a) - 1
    for i in xrange(2, n):
        k = a[i]
        j = i - 1
        while j >= 0 and a[j] > k:
            a[j+1] = a[j]
            j -= 1
        a[j+1] = k
    return a

def mergesort(a):
    mid = len(a)//2
    lft, rgt = a[:mid], a[mid:]
    if len(lft) > 1: lft = mergesort(lft)
    if len(rgt) > 1: rgt = mergesort(rgt)
    res = []
    while lft and rgt:
        if lft[-1] >= rgt[-1]:
            res.append(lft.pop())
        else:
            res.append(rgt.pop())
    res.reverse
    return (lft or rgt) + res


print mergesort([3, 9, 10, 14])
decks = {}
cards = []
for line in stdin:
    index, deck = line.split(':')
    deck = [int(x) for x in deck.split(',')]
    cards.extend(deck)
    decks[index] = deck
print cards
sorted_cards = mergesort(cards)
print sorted_cards
out = ''
for card in sorted_cards:
    for k, v in decks.iteritems():
        if card in v: 
            out += k 
            break
print out
