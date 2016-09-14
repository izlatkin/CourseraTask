__author__ = 'ilya'


#ans 40730


def factorial(n):
    if n == 1:
        return 1
    else:
        return n * factorial(n - 1)


def factorial2(n):return reduce(lambda x,y:x*y,[1]+range(1,n+1))


def split_number_digits(n):
    lt = []
    st = str(n)
    for i in range(0, len(st)):
        lt.append(factorial2(int(st[i])))
    return sum(lt)



def main():
    print factorial(5)
    print split_number_digits(145)
    for i in range(10, 10 ** 7):
        if i == split_number_digits(i):
            print i
    #n = 2
    #print factorial(n + 1) - 10 ** n




if __name__ == '__main__':
    main()