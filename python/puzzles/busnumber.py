from sys import stdin

n = int(stdin.readline())
numbers = sorted([int(v) for v in stdin.readline().split(' ')])

subsecs = []
sec = [numbers[0]]
for i in xrange(n-1):
    if numbers[i+1]-numbers[i] == 1:
        sec.append(numbers[i+1])
    else:
        subsecs.append(sec)
        sec = [numbers[i+1]]
subsecs.append(sec)

out = ''
for subsec in subsecs:
    m = len(subsec)
    if m > 2:
        out += str(subsec[0]) + '-' + str(subsec[m-1]) + ' '
    else:
        out += ' '.join(str(x) for x in subsec) + ' '

print out
