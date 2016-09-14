__author__ = 'ilya'

# https://projecteuler.net/problem=15


# ansewer = 137846528820

globvar = 0


def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)


def shortcuts(n, k):
    return factorial(n) / (factorial(n - k) * factorial(k))


def move(n, x, y):
    global globvar
    if x < n:
        if y == n:
            globvar += 1
        else:
            move(n, x + 1, y)
    if y < n:
        if x == n:
            globvar += 1
        else:
            move(n, x, y + 1)



def main():
    global globvar
    results = []
    move(20, 11, 9)
    print globvar
    for i in range(1, 20):
        globvar = 0
        move(20, i, 20 - i)
        results.append(globvar * shortcuts(20, i))
    print results
    print sum(results) + 2


if __name__ == '__main__':
    main()
