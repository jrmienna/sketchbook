from sys import stdin

split = stdin.readline().split(" ")

h = int(split[0])
root = 2**(h+1) - 1

i = 0
if len(split) > 1:
    seq = split[1]
    for char in seq:
        if char == 'L':
            i = i*2 + 1
        if char == 'R':
            i = i*2 + 2

print root - i
