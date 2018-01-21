from sys import stdin

contestants = []
for line in stdin.readlines():
    grades = sum([int(c) for c in line.split(" ")])
    contestants.append(grades)

score = max(contestants)
winner = contestants.index(score) + 1

print str(winner) + ' ' + str(score)
