__author__ = 'ilya'

#1/100



from math import sqrt




def divider(n):
    lst = []
    for i in range(2, n / 2 + 1):
        if n % i == 0:
            lst.append(i)
    lst.append(n)
    return lst


def simplify(n, m):
    #print divider(n)
    #rint divider(m)

    new_n = n
    new_m = m
    while True:
        numerator = divider(new_n)
        denominator = divider(new_m)
        print numerator
        print denominator
        lst = list(set(numerator) & set(denominator))
        print lst
        if len(lst) == 0:
            break
        #for i in lst:
        new_n = new_n // lst[0]
        new_m = new_m // lst[0]
        print new_n
        print new_m
        print "====="
    return [new_n, new_m]


def find_curious_fraction():
    for a in range(0, 10):
        for b in range(0, 10):
            for c in range(0, 10):
                i = str(a)+str(b)+'.0'
                j = str(b)+str(c)+'.0'
                if a != 0 and a != c and float(j) != 0.0 and c != 0 and (float(i)/float(j) == float(str(a)+'.0') / float(str(c)+'.0')):
                    print i, "/", j, '=', a, "/", c



def sockes_proble():
    print divider(2014)
    print divider(503 ** 2)
    print 503 ** 2
    ls = []
    for j in range(2,2014):
        ls.append(j ** 2)
    print(ls)


    for i in range(1, 2014):
        r = float(i+0.0) ** 2 / (2 * float(i+0.0) + 1)
        b = float(i+0.0)
        if 32 * i ** 2 + 8 * i + 4 in ls:
            print i, ((1 + 4 * i) + sqrt(32 * i ** 2 + 8 * i + 4)) / 2, ((1 + 4 * i) - sqrt(32 * i ** 2 + 8 * i + 4)) / 2
        #print r % 1
    for i in range(1, 2014):
        for j in range(0,i):
            if 2 * (j * (j - 1) + (i - j) * (i - j -1)) == i * (i - 1):
                print i, j


def digit_cancelling_fractions():
    product = 1

    for b in range(1, 10):  # Denominator
        for a in range(1, b):  # Numerator
            q, r = divmod(9 * a * b, 10 * a - b)  # Solving for c

            if not r and q < 10:  # If Integer and c < 10
                product *= float(b) / a  # Multiplies Denominators

    return int(product)


def main():
    #print simplify(32, 256)
    #print len([])
    find_curious_fraction()
    #print round(float(10 / 9), 4)
    #print 10 / 105.0 + 0.0
    simplify(16 * 19 * 26 * 49, 64 * 95 * 65 * 98)
    print digit_cancelling_fractions()






if __name__ == '__main__':
    main()