'''
Gnomesort

The algorithm alternately scans upward in the sequence 
for an out-of-place (that is, too small) element and 
moves that element down to a valid position (by repeated swapping).

Running time:
    Omega(n)
    O(n^2)
'''
def gnomesort(seq):
    i = 0
    while i < len(seq):
        if i == 0 or seq[i-1] <= seq[i]:
            i += 1
        else:
            seq[i], seq[i-1] = seq[i-1], seq[i]
            i -= 1

