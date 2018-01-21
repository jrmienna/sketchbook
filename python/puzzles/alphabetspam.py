from sys import stdin

d = {'whitespace': 0, 'uppercase' : 0, 'lowercase': 0, 'symbol': 0}

w = '_'

string = stdin.readline()
n = len(string)-1
for i in xrange(n):
    typ = ''
    if(string[i] == '_' or string[i] == ' '):
        typ = 'whitespace'
    elif(string[i].isalpha()):
        if(string[i].isupper()):
            typ = 'uppercase'
        else:
            typ = 'lowercase'
    else:
        typ = 'symbol'
    
    d[typ] = d[typ] + 1

s = sum(d.values())

print '{:10.16f}'.format(float(d['whitespace'])/s)
print '{:10.16f}'.format(float(d['lowercase'])/s)
print '{:10.16f}'.format(float(d['uppercase'])/s)
print '{:10.16f}'.format(float(d['symbol'])/s)
