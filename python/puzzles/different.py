from sys import stdin

split = [int(x) for x in stdin.readline().split(' ')]
print abs(split[0] - split[1])
