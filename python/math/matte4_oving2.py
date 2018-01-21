# -*- coding: utf-8 -*-
import numpy as np  
import matplotlib.pyplot as plt   

def lk(X, x, k):
    result = 1.0
    for i in range (0, len(x)):
        if i != k:
            num = X - x[i]
            div = x[k] - x[i]
            result *= num/div
    return result

def lagrange (X, x, f):
    F = np.zeros(len(X))
    for i in range (0, len (F)):
        for k in range (0, len(f)) :
            F[i] += lk(X[i], x, k) * f[k]
    return F

x = np.array([-1.5, -0.75, 0, 0.75, 1.5])  # Interpoleringspunkter
f = np.array([-14.1014, -0.931597, 0.0, 0.931597, 14.1014]) # Kjente verdier.
X = np.linspace(-1.5, 1.5, 100) # 100 jevnt fordelte punkter fra -1,5 til 1,5
F = lagrange(X, x, f)  # OBS: I en tidligere utgave av koden stod det
# her "interpolate" istedet for "lagrange". Beklager!

# Følgende plottekode kan bare kopieres direkte.
fig = plt.figure()
ax = fig.add_subplot(111)
ax.plot(x, f, 'o', color='black', label='kjente punkter')
ax.plot(X, np.tan(X), color='black', linestyle='dashed', label='tan')
ax.plot(X, F, color='black', label='interpolert')
ax.set_xlim(-1.55, 1.55)
ax.legend(loc='upper left')
plt.show()
