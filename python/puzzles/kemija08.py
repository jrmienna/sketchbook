from sys import stdin

vowels = 'aeiou'
line = stdin.readline().strip()

i = 0
while i < len(line) - 1:
    if line[i] in vowels:
        line = line[:i] + line[i+2:]
    i += 1

print line.strip()
