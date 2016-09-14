__author__ = 'ilya'

# The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
# Find the sum of all the primes below two million.


# ansewe = 142913828922

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

    lst = find_prime_sequence_fast(10)
    print(sum(lst))
    lst = find_prime_sequence_fast(2000000)
    print(sum(lst))


if __name__ == '__main__':
    main()
