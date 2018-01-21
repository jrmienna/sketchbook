from sys import stdin
from random import randint

THRESHOLD = 10

def insertion_sort(a):
    n = len(a) - 1
    for i in xrange(0, n):
        k = a[i]
        j = i - 1
        while j >= 0 and a[j] > k:
            a[j+1] = a[j]
            j -= 1
        a[j+1] = k
    return a

def partition(a):
    piv = a.pop(randint(0, len(a) - 1))
    lo = [x for x in a if x[0] <= piv]
    hi = [x for x in a if x[0] > piv]
    return lo, piv, hi

def quick_sort(a):
    n = len(a)
    if n < 2:
        return a
    elif n < THRESHOLD:
        return insertion_sort(a)
    else:
        lo, piv, hi = partition(a)
        return quick_sort(lo) + [piv] + quick_sort(hi)

def merge(decks):
    mid = len(decks) // 2
    left, right = decks[:mid], decks[mid:]
    if len(left) > 1: left = merge(left)
    if len(right) > 1: right = merge(right)
    res = []
    while left and right:
        if left[-1] >= right[-1]:
            res.append(left.pop())
        else:
            res.append(right.pop())
    res.reverse()
    return (left or right) + res


decks = []
for line in stdin:
    (index, deck) = line.split(':')
    decks.extend([(int(card), index) for card in deck.split(',')])
out = ''
decks = quick_sort(decks)
for card in decks:
    out += card[1]
print out
