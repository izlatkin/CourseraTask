# coding=utf-8


# A palindromic number reads the same both ways. The largest
# palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
# Find the largest palindrome made from the product of two 3-digit numbers.
# Anwer 906609
#[(913, 993)]


from math import sqrt

def divider(n):
    lst = []
    k = int(sqrt(n))
    for i in xrange(100, k):
        if n % i == 0:
            if len(str(n / i)) == 3:
                lst.append((i, n / i))

    return lst

def makepolidrom(num):
    kk = str(num)[::-1]
    out = str(num) + kk
    return int(out)


def main():
    lst = divider(555555)
    print(lst)
    print(makepolidrom(156))

    for i in xrange(500,999):
        print(i)
        print(divider(makepolidrom(i)))

#906
#[(913, 993)]

if __name__ == '__main__':
    main()
