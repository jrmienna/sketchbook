from sys import stdin

d = {
    '111101101101111':'0',
    '001001001001001':'1',
    '111001111100111':'2',
    '111001111001111':'3',
    '101101111001001':'4',
    '111100111001111':'5',
    '111100111101111':'6',
    '111001001001001':'7',
    '111101111101111':'8',
    '111101111001111':'9',
}

longest_prefix = 10
n = 5*3

line = stdin.readline()
numbs = [line[i:i+3] for i in xrange(0, len(line), 4)]
m = len(numbs)
for line in stdin.readlines():
    row = [line[i:i+3] for i in xrange(0, len(line), 4)]
    for i in xrange(m):
        numbs[i] += row[i]

numbs = [i.replace(" ", "0").replace("*", "1") for i in numbs]

res = ''
for numb in numbs:
    if numb in d.keys():
        res += d[numb]

print "BEER!!" if int(res) % 6 == 0 else "BOOM!!"
