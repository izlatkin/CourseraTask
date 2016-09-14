__author__ = 'ilya'

# coding=utf-8


# By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
# What is the 10 001st prime number?

# 10 001th prime number 104743


def find_prime_sequence_fast(n):
    #n = 13195
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


def main():
    a = find_prime_sequence_fast(500000)
    print(len(a))
    print("10 001th prime number " + str(a[10000]))


if __name__ == '__main__':
    main()