#-*- coding: utf-8 -*-
from scipy.linalg import lu_factor, lu_solve
import numpy as np

a = np.array([
            [ 2, 1, 2],
            [-2, 2, 1],
            [ 1, 2,-2]
            ])

l_og_u, p = lu_factor(a)

print "--------------------------"
print "--------Oppgave 1---------"
print l_og_u
print p

b = np.array([
            [ 0],
            [ 0],
            [18]
            ])

print "Løsning:"
print lu_solve((l_og_u, p), b)

print "--------------------------"
print "--------Oppgave 2---------"

def foo(a, b, x0, eps):
    n = x0.shape[0]
    x = np.zeros(n)
    for j in range(0, n):
        x[j] = x0[j]

    done = False
    while not done:
        xprev = x
        for j in range(0, n):
            xprev[j] = x[j]
        for j in range(0, n):
            x[j] = b[j]
            for k in range(0, j):
                x[j] = x[j] - a[j, k]*x[k]
            for k in range(j+1, n):
                x[j] = x[j] - a[j, k]*xprev[k]
            x[j] = x[j] / a[j, j]

        # Start på konvergenstest.
        i = 0
        for j in range(0, n):
            if abs(x[j] - xprev[j]) > abs(x[i] - xprev[i]):
                i = j
        if abs(x[i] - xprev[i]) < eps*abs(x[i]):
            done = True
        # Slutt på konvergenstest.

    return x

a = np.array([
            [ 1.00, -0.25, -0.25,  0.00],
            [-0.25,  1.00,  0.00, -0.25],
            [-0.25,  0.00,  1.00, -0.25],
            [ 0.00, -0.25, -0.25,  1.00]
            ])
b = np.array([
            [50.0],
            [50.0],
            [25.0],
            [25.0],
            ])
x0 = np.array([
            [1.0],
            [1.0],
            [1.0],
            [1.0]
            ])

eps = 0.00001
print foo(a, b, x0, eps)

print "--------------------------"
