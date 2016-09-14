__author__ = 'ilya'

# Smallest multiple
# 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
# What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?


# answer 11496 input = 232792560

def find_prime_sequence(n):
    # n = 13195
    listex = []

    for i in xrange(2, n + 1):
        for j in xrange(2, i):
            if i % j == 0:
                break
        else:
            listex.append(i)

    return listex


def factorial(n):
    return reduce(lambda x, y:
                  x * y,
                  [1]+range(1, n + 1))


def checkdiv(n):
    for i in [2, 3, 4, 5, 7, 9, 11, 13, 16, 17, 19, 20]:
        if n % i != 0:
            return False
    return True


def main():
    # print(factorial(20))
    # print(checkdiv(factorial(20)))
    lst = find_prime_sequence(20)
    print(lst)
    #lst.append(20)

    step = 1
    for i in lst:
        step *= i

    print("step " + str(step))

    # 4 9 16 20

    print(str(4*9*16*20))

    lst = [2, 3, 4, 5, 7, 9, 11, 13, 16, 17, 19, 20]
    start = 1
    for i in lst:
        start *= i
    print("start " + str(start))
    i = start

    print("start/step " + str(start/step))

    print(checkdiv(start))
    print(checkdiv(step))
    print(checkdiv(9699690))
    print(checkdiv(111730729110))

    for i in xrange(1, start/step):
        inputn = start - i * step
        if checkdiv(inputn):
            print(str(i) + " input = " + str(inputn))
    #while checkdiv(start):
    #    print(i)
    #    start += 20;



if __name__ == '__main__':
    main()
