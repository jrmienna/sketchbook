from sys import stdin
from random import randint

def max_heapify(a):
    '''
    l = left(i) 
    r = right(i) 
    if l ≤ heap-size[A] and A[l] > A[i] 
        then largest = l 
        else largest = i 
    if r ≤ heap-size[A] and A[r] > A[largest] 
        then largest = r 
    if largest ≠ i 
        then exchange A[i]↔ A[largest] 
            Max-Heapify(A,largest)
    '''
    pass

def build_max_heap(a):
    n = len(a) - 1
    for i in xrange(n, -1, -1):
        max_heapify(a, i)

def heapsort(a):
    n = len(a) - 1
    for i in xrange(n, -1, -2):
        a[1] = a[i]
        heap_size -= 1
        max_heapify(a, 1)


decks = []
for line in stdin:
    (index, deck) = line.split(':')
    decks.extend([(int(card), index) for card in deck.split(',')])
out = ''
decks = quick_sort(decks)
for card in decks:
    out += card[1]
print out
