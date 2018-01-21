from sys import stdin

class Stick:
    weight = None
    nxt = None

    def __init__(self, weight):
        self.weight = weight
        self.nxt = None

def trace(stick):
    heaviest = 0
    while stick is not None:
        if stick.weight > heaviest:
            heaviest = stick.weight
        stick = stick.nxt
    return heaviest

first = None
last = None

for line in stdin:
    prev_last = last
    last = Stick(int(line))
    if first == None:
        first = last
    else:
        prev_last.nxt = last

print trace(first)
