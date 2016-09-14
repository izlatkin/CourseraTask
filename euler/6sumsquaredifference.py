__author__ = 'ilya'
# coding=utf-8

# The sum of the squares of the first ten natural numbers is, 385
# The square of the sum of the first ten natural numbers is, (1 + 2 + ... + 10)^2 = 55^2 = 3025
# Hence the difference between the sum of the squares of the first ten natural
# numbers and the square of the sum is 3025 − 385 = 2640.
# Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.


# answer diff 25164150

def sumsquare(n):
    summ = 0;
    for i in xrange(1, n + 1):
        print(str(i) + " " + str(i**2))
        summ += i**2
    return summ


def main():
    a = sumsquare(100)
    print("sum of the squares " + str(a))
    b = sum(range(1, 101))
    print("square of the sum " + str(b))
    print("diff " + str(b**2 - a))


if __name__ == '__main__':
    main()
