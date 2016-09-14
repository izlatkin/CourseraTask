from math import sqrt

__author__ = 'ilya'

# The prime factors of 13195 are 5, 7, 13 and 29.
# What is the largest prime factor of the number 600851475143 ?
# 6857


def find_prime_sequence(n):
    # n = 13195
    listex = []

    for i in xrange(2, n + 1):
        for j in xrange(2, i):
            if i % j == 0:
                break
        else:
            listex.append(i)

    return listex

def find_prime_sequence_fast(n):
    #n = 13195
    """

    :rtype : counter
    """
    lst = [2]
    for i in xrange(3, n + 1, 2):
        if (i > 10) and (i % 10 == 5):
            continue
        for j in lst:
            if j * j - 1 > i:
                lst.append(i)
                break
            if i % j == 0:
                break
        else:
            lst.append(i)

    return lst


def prime_factors_of_number(n, input_list):
    lst = []
    for i in input_list:
        if n % i == 0:
            lst.append(i)

    return lst


def main():

    nn = 600851475143

    lst = find_prime_sequence_fast(int(sqrt(nn)))
    print(lst)

    prime = prime_factors_of_number(nn, lst)
    print(prime)

    #list = findPrimeSequence(600851475143)
    #print(list)


if __name__ == '__main__':
    main()