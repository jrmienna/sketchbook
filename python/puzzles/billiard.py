from sys import stdin

for line in stdin.readlines():
    numbs = [int(v) for v in line.split(' ')]
    a = numbs[0]  # horizontal side
    b = numbs[1]  # vertical side
    s = numbs[2]  # seconds before ball returns
    m = numbs[3]  # bounces of vertical side
    n = numbs[4]  # bounces of horizontal side

