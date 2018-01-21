from sys import stdin
from math import floor

def out(s, d):
    if s < d or s < 0 or d < 0:
        return 'impossible'
    first = int((s + d)/2)
    second = int((s - d)/2)
    if first < second:
        return str(second) + ' ' + str(first)
    return str(first) + ' ' + str(second)

n = int(stdin.readline())
for i in xrange(n):
    split = stdin.readline().split(' ')
    s = int(split[0])
    d = int(split[1].strip())
    print out(s,d)
