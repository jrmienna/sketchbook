'''
theta(n^2)
theta(nlg n) (expected)
'''

def partition(a):
    piv = choose_piv(a, ...)
    # ...
    lo = [x for x in a if x <= piv]
    hi = [x for x in a if x > piv]
    return lo, piv, hi

def quick_sort(a):
    n = len(a)
    if n < 2:
        return a
    elif n < INSERTION_THRESHOLD:
        return insertion_sort(a)
    else:
        lo, piv, hi = partition(a)
        return quick_sort(lo) + [piv] + quick_sort(hi)
