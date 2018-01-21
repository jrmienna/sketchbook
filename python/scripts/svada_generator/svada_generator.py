import sys
import random

#file = sys.argv[1]

filename = "svada_software.txt"


words1 = []
words2 = []

divided = False
with open(filename) as f:
    for line in f.readlines():
        if line == '\n':
            divided = True
        if not divided:
            words1.append(line)
        else:
            words2.append(line)

imax1 = len(words1)
imax2 = len(words2)
rand11 = random.randint(0, imax1)
rand12 = random.randint(0, imax1)
rand2 = random.randint(0, imax2)

if imax2 > 0:
    print words1[rand11] + " " + words2[rand2]
else:
    print words1[rand11] + " " + words1[rand12]
