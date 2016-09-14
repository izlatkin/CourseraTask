import matplotlib

__author__ = 'ilya'

from scipy import optimize
import numpy as np
from matplotlib import pylab as plt

def f(x):
    return (x[0] - 3.2) ** 2 + (x[1] - 0.1) ** 2 + 3

print f([3.2, 0.1])

x_min = optimize.minimize(f, [5, 5])
print x_min
print x_min.x

from scipy import linalg

a = np.array([[3, 2, 0], [1, -1, 0], [0, 5, 1]])
b = np.array([2, 4, -1])

x = linalg.solve(a, b)
print x


print np.dot(a, x)

X = np.random.randn(4, 3)
U, D, V = linalg.svd(X)
print U.shape, D.shape, V.shape
print type(U), type(D), type(V)

#matplotlib inline


plt.plot([1, 2, 3, 4], [1, 4, 9, 16])
plt.show()

x = np.arange(-10, 10, 0.1)
y = x ** 3
plt.plot(x, y)
plt.show()