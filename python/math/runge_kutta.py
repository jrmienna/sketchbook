'''
y' - xy**2 = 0
y(0) = 1
soultion:
    y = 1/(1-0.5*x**2)
'''
def f(x, y):
    return x*(y**2)

def exact(x):
    return 1/(1-0.5*x**2)

h = 0.1

x = 0
y_exact = 1
exact_list = []

for i in xrange(10):
    y_exact = exact(x)
    exact_list.append(y_exact)
    x += h

x = 0
y = 1
nummeric_list = []

for i in xrange(10):
    nummeric_list.append(y)
    k1 = h * f(x,y)
    k2 = h * f(x+h/2, y+k1/2)
    k3 = h * f(x+h/2, y+k2/2)
    k4 = h * f(x+h, y+k3)
    y = y + (1/6)*(k1 + 2*k2 + 2*k3 + k4)
    x+=h
    
print exact_list
print nummeric_list

error_list = []
for i in xrange(10):
    error_list.append(exact_list[i] - nummeric_list[i])

print error_list
