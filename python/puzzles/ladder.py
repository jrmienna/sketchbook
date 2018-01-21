from sys import stdin
from math import ceil, sin, pi

line = stdin.readline().split(" ")
h = int(line[0])
v = int(line[1]) * pi / 180
print int(ceil(h / sin(v)))
