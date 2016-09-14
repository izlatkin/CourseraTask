__author__ = 'ilya'


def do_collatz(n):
    if n % 2 == 0:
        return n / 2
    else:
        return 3 * n + 1


def lenght_collats(n):
    i = 1
    while n != 1:
        n = do_collatz(n)
        i += 1
    return i

def main():
    max_lenght = 1
    number = 1
    for i in range(1, 1000000):
        if lenght_collats(i) > max_lenght:
            number = i
            max_lenght = lenght_collats(i)

    print max_lenght
    print number





if __name__ == '__main__':
    main()