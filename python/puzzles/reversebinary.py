from sys import stdin
from math import floor

numb = int(stdin.readline())

binary = []
while numb >= 1:
    binary.append(numb % 2)
    numb = int(floor(numb/2))

res = 0
exp = len(binary)-1
for val in binary:
    res += val*2**exp
    exp -= 1

print res
