__author__ = 'ilya'


def find_prime_sequence_fast(start, finish):
    #n = 13195
    lst = [2]
    for i in xrange(start, finish + 1):
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

    nn = 999999

    lst = find_prime_sequence_fast(10000, 99999)
    del lst[0]
    print(lst)


if __name__ == '__main__':
    main()
