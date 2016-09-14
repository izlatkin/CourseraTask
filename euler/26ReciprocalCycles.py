import decimal

__author__ = 'ilya'


#1 / 983
# 982



def max_pereodic(st):
    max_p = 1
    for i in range(0, 30):
        current_p = find_pereodic(st[i:])
        if current_p > max_p:
            max_p = current_p
    return max_p


def find_pereodic(st):
    #k = 8
    #print st[2:k]
    #j = 2 * k - 2
    #print st[k:j]
    #if st[2:k] == st[k:j]:
    #    return 1000
    for i in range(1, len(st) / 2):
        j = 2 * i
        if st[0:i] == st[i:j]:
            return i



def main():
    d = float(1564164641564151531513547)
    print "%.2000f" %float(1 / d)
    decimal.getcontext().prec = 2000
    dd = decimal.Decimal(1) / decimal.Decimal(6)
    long_number = str(dd)
    print long_number
    #print find_pereodic(long_number[2:])
    longest = []
    for i in range(2, 1000):
        dd = decimal.Decimal(1) / decimal.Decimal(i)
        long_number = str(dd)
        print 1, "/", i
        n = max_pereodic(long_number[2:])
        longest.append(n)
        print n

    print longest
    print max(longest)


if __name__ == '__main__':
    main()