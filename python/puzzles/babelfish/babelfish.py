from sys import stdin

d = {}
words = []

line = stdin.readline()
while len(line) > 1:
    split = line.split(' ')
    d[split[1].strip()] = split[0]
    line = stdin.readline()

for line in stdin.readlines(): 
    word = line.strip()
    if word in d.keys():
        print d[word]
    else:
        print 'eh'
