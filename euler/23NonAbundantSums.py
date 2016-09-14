__author__ = 'ilya'



##    4179871

def n_divisors(n):
    lst = [1]
    for i in range(2, n / 2 + 1):
        if n % i == 0:
            lst.append(i)

    return lst


def n_abundant(limit):
    out = []
    for i in range(1, limit):
        if sum(n_divisors(i)) > i:
            #print n_divisors(i), i
            print i
            out.append(i)

    return out


def all_non_sum_abundant_n(lst):
    out = []
    #for i in range(24, 28123):
    for i in range(1, 24):
        notsum = 0;
        for j in lst:
            if (i - j) in lst:
                notsum = 1
            if j > i:
                break
        if notsum == 0:
            print i
            out.append(i)

    return out





def main():
    #lst = n_divisors(284)
    #print lst
    #lst = n_abundant(28123)
    lst = n_abundant(28123)
    out = all_non_sum_abundant_n(lst)
    print out
    print sum(out)
    #4179595    + 276
    #    276
#    4179871



if __name__ == '__main__':
    main()