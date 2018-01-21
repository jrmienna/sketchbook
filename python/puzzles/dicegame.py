from sys import stdin

gunnar = sum([int(val) for val in stdin.readline().split(" ")])
emma = sum([int(val) for val in stdin.readline().split(" ")])

if gunnar > emma:
    print "Gunnar"
elif gunnar < emma:
    print "Emma"
else:
    print "Tie"
