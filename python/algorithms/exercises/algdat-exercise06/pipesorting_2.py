from sys import stdin

INS_TH = 32

def insertion_sort(a):
    n = len(a)
    for i in xrange(1, n):
        j = i
        while j > 0 and a[j-1] > a[j]:
            a[j-1], a[j] = a[j], a[j-1]
            j -= 1
    return a

def partition(a):
    piv, a = a[0], a[1:]
    lo = [x for x in a if x <= piv]
    hi = [x for x in a if x > piv]
    return lo, piv, hi

def quick_sort(a):
    n = len(a)
    if n < 2:
        return a
    elif n < INS_TH:
        return insertion_sort(a)
    else:
        lo, piv, hi = partition(a)
        return quick_sort(lo) + [piv] + quick_sort(hi)

def binsearch(a, value):
    l = 0
    r = len(a) - 1
    while l <= r:
        m = (l + r) >> 1
        if a[m] < value:
            l = m + 1
        elif value < a[m]:
            r = m - 1
        else:
            break
    return m

def find(a, lower, higher):
    lo_index = binsearch(A, lower)
    hi_index = binsearch(A, higher) 
    if a[lo_index] > lower and lo_index != 0:
        lo_index -= 1
    if a[hi_index] < higher and hi_index != len(a) - 1:
        hi_index += 1
    return a[lo_index], a[hi_index] 

A = quick_sort([int(x) for x in stdin.readline().split()])
for line in stdin:
    bounds = [int(x) for x in line.split()]
    imin, imax = find(A, bounds[0], bounds[1])
    print str(imin) + " " + str(imax)
