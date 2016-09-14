import timeit
import numpy as np
from scipy import optimize


x = [2, 3, 4, 6]
y = np.array(x)

print type(x), x
print type(y), y


print x[1:3]
print y[1:3]
print y[[0, 2]]
print y[y>3]
print x * 5
print y * 5
print y ** 2
matrix = [[1, 2, 4], [3, 1, 0]]
nd_array = np.array(matrix)

print np.random.randn()
print np.random.randn(4, 5)
print np.arange(0, 8, 0.1)

#print timeit(np.arange(0, 10000))
#print timeit(range(0, 10000))