'''
Theta(nlg n)
'''
def mergesort(seq):
    mid = len(seq)//2                       # Midpoint for division
    lft, rgt = seq[:mid], seq[mid:]
    if len(lft) > 1: lft = mergesort(lft)   # Sort by halves
    if len(rgt) > 1: rgt = mergesort(rgt)
    res = []
    while lft and rgt:
        if lft[-1] >= rgt[-1]:              # Left has greatest last value
            res.append(lft.pop())
        else:                               # Right has greatest last value
            res.append(rgt.pop())
    res.reverse()                           # Result it backward
    return(lft or rgt) + res                # Also add the remiander

list_ = [0, 5, 10, 2, 5]
print mergesort(list_)
