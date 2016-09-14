from math import sqrt

__author__ = 'ilya'

# coding=utf-8

# We can see that 28 is the first triangle number to have over five divisors.
# What is the value of the first triangle number to have over five hundred divisors?
# https://projecteuler.net/problem=12


# Answer = 76576500

def n_divisors(n):
    lst = 2
    for i in range(2, n / 2 + 1):
        if n % i == 0:
            lst += 1
            #lst.append(i)


    #print(lst)
    return lst

def n_divisors_new(n):
    lst = 2
    i = 2;
    limit = n / 2 + 1
    while i <= limit:
        if n % i == 0:
            limit = n / i + 1
            lst += 2
            i += 1

    #print(lst)
    return lst


def triangle_subsequanse():
    n = 1
    j = 1
    k = n_divisors(n)
    while k < 200:
        j += 1
        n += j
        k = n_divisors(n)
        #print(str(j) + " : " + str(n) + " : " + str(k) + " " + str(len(k) + 2))
        print(str(j) + " : " + str(n) + " : " + str(k))

# 2079 : 2162160 : 320
# 4637 : 10753203 : 8
# 5984 : 17907120 : 480
# 12375 : 76576500 : 576

def triangle_subsequanse_up(xx, start):
    n = start
    j = xx
    #k = n_divisors(n)
    k = len(list(divisorGenerator(n)))
    while k < 500:
        j += 1
        n += j
        #k = n_divisors(n)
        k = len(list(divisorGenerator(n)))
        #print(str(j) + " : " + str(n) + " : " + str(k) + " " + str(len(k) + 2))
        print(str(j) + " : " + str(n) + " : " + str(k))


def divisorGenerator(n):
    large_divisors = []
    for i in xrange(1, int(sqrt(n) + 1)):
        if n % i is 0:
            yield i
            if i is not n / i:
                large_divisors.insert(0, n / i)
    for divisor in large_divisors:
        yield divisor


def main():
    #print n_divisors(4)
    #triangle_subsequanse()
    #triangle_subsequanse_up(5984, 17907120)
    triangle_subsequanse_up(2, 3)
    #print list(divisorGenerator(2162160))
    #print len(list(divisorGenerator(2162160)))




if __name__ == '__main__':
    main()
