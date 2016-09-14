__author__ = 'ilya'


from scipy import optimize


def f(x):   # The rosenbrock function
    return .5*(1 - x[0])**2 + (x[1] - x[0]**2)**2

print f([1, 1])


result = optimize.brute(f, ((-5, 5), (-5, 5)))
print result

print optimize.differential_evolution(f, ((-5, 5), (-5, 5)))


import numpy as np

def g(x):
    return np.array((-2*.5*(1 - x[0]) - 4*x[0]*(x[1] - x[0]**2), 2*(x[1] - x[0]**2)))


print optimize.check_grad(f, g, [2, 2])