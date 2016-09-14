__author__ = 'ilya'


# answer = 648
# https://projecteuler.net/problem=20


def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)


def main():
    num_str = factorial(100)
    print num_str
    print(num_str)
    num_list = []
    print num_str.__sizeof__()

    num_str = str(num_str)
    for i in range(0, len(num_str)):
        num_list.append(int(num_str[i:i + 1]))
        #print num_list

    print sum(num_list)


if __name__ == '__main__':
    main()