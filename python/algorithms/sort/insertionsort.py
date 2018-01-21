'''
Theta(n^2)
'''

def ins_sort_iterative(a):
    '''
    OBS: xrange finnes ikke i python-3, der er range=xrange
    Ide: venstre siden av a er alltid sortert, mens høyresiden er alle
    orsorterte elementer. While elementet vi skal flytte (key) fra høyresiden
    til venstresiden - dvs. fra usortert til sortert - er midre enn elementet
    til venstre for den OG vi ikke har nådd venstreenden (j == 0), do: swap
    med elementet til venstre.
    '''
    n = len(a)
    for i in xrange(2, a.length):
        key = a[i]
        j = i - 1
        while j >= 0 and key < a[j]:
            a[j+1] = a[j]
            j -= 1
        a[j+1] = k

def ins_sort_recursive(a):
    pass
