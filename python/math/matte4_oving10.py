import matplotlib.pyplot as plt
import numpy as np

def test():
    fig = plt.figure()
    k = 0.01
    c = 1.0
    x = np.linspace(0, 1, 1000)
    y = np.zeros(1000)
    N = 1000

    for p in range(0, 12):
        ax = fig.add_subplot(6, 2, p+1)
        ax.set_xlim(0, 1)
        ax.set_ylim(-0.0026, 0.0026)
        t = 0.15*p
        ax.set_title(r'$t=%g$' %(t))
        y = np.zeros(1000)
        for n in range(0, N):
            y += 1.0/(2*n + 1.0)**3*np.cos((2*n+1)*np.pi*t)*np.sin((2*n+1)*np.pi*x)
        y *= 8.0*k/np.pi**3
        ax.plot(x,y, color='black')
    plt.show()

test()
