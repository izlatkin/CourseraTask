__author__ = 'ilya'


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


def truncatable_check(n, ls):
    s = str(n)
    b = str(n)
    while len(str(s)) > 1:
        print s[1:], b[:-1]
        s = s[1:]
        b = b[:-1]
        if int(s) not in ls:
            return False
        if int(b) not in ls:
            return False

    return True


def main():
    prime_sequence = find_prime_sequence_fast(10000)
    prime_sequence.append(1)
    print prime_sequence
    #print((37 in prime_sequence))
    print truncatable_check(3797, prime_sequence)
    print truncatable_check(41, prime_sequence)

    lst = []
    for i in prime_sequence:
        if i > 10:
            if truncatable_check(i, prime_sequence):
                lst.append(i)

    print lst
    print sum(lst)
    ss = 0
    for i in range(1, 12):
        print i, lst[i]
        ss += lst[i]

    print ss


if __name__ == '__main__':
    main()