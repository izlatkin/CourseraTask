__author__ = 'ilya'

import itertools


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


def is_prime(n):
    for j in range(2, n):
        if n % j == 0:
            return False
    return True


def split_number_digits(n):
    lt = []
    st = str(n)
    for i in range(0, len(st)):
        lt.append(int(st[i]))
    return lt


def check_digits(ls):
    wrong_digits = [2, 4, 5, 6, 8, 0]
    for j in ls:
        if j in wrong_digits:
            return False
    return True


def check_all_permutation(n):
    for p in itertools.permutations(split_number_digits(n)):
        tmp = ''
        for i in p:
            tmp += str(i)
        if is_prime(int(tmp)):
            return False
    return True




def main():
    print is_prime(719)
    wrong_digits = [2, 4, 5, 6, 8, 0]
    list_of_numbers = []
    for i in range(1, 1000000):
        if check_digits(split_number_digits(i)):
            #print i
            if check_all_permutation(i):
                print i
                list_of_numbers.append(i)

    print(list_of_numbers)
    print(len(list_of_numbers))
    #print check_all_permutation(2345)


if __name__ == '__main__':
    main()
