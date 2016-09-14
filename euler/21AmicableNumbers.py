__author__ = 'ilya'




#
# Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
# If d(a) = b and d(b) = a, where a != b, then a and b are an amicable pair and each of a and b are called amicable numbers.


# anser = 31626

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


def n_divisors(n):
    lst = [1]
    for i in range(2, n / 2 + 1):
        if n % i == 0:
            lst.append(i)


    #lst.append(n)
    return sum(lst)


def main():
    lst = n_divisors(284)
    print lst
    lst = n_divisors(220)
    print lst
    print
    sm = 0
    range_limit = 10000
    for i in range(1, range_limit):
        tmp = n_divisors(i)
        if tmp < range_limit and n_divisors(tmp) == i and i != tmp:
            print(str(i) + " " + str(n_divisors(i)))
            sm += (i)
        #print(str(i) + " " + str(n_divisors(i)))

    print sm



if __name__ == '__main__':
    main()