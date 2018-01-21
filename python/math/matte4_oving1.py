import math

def f(x):
    return x - math.cos(x)

def secant(x0, x1, e=1e-32):
    while 1:
        dx = f(x1)*(x1 - x0) / float(f(x1) - f(x0))
        if abs(dx) < e:
            return x1
        x0, x1 = x1, x1-dx
        print x1 #se hver iterasjon
    return x1

def main():
    print secant(1, 0.5) 

if __name__ == "__main__":
    main()
