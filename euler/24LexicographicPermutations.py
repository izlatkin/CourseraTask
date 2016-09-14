import itertools
__author__ = 'ilya'


#anser (2, 7, 8, 3, 9, 1, 5, 4, 6, 0)


def check_permutation(number):
    input_numbet = str(number)
    if input_numbet.find('0') == -1:
        return 0
    if input_numbet.find('1') == -1:
        return 0
    if input_numbet.find('2') == -1:
        return 0
    if input_numbet.find('3') == -1:
        return 0
    if input_numbet.find('4') == -1:
        return 0
    if input_numbet.find('5') == -1:
        return 0
    if input_numbet.find('6') == -1:
        return 0
    if input_numbet.find('7') == -1:
        return 0
    if input_numbet.find('8') == -1:
        return 0
    if input_numbet.find('9') == -1:
        return 0
    return 1


def factorial(n):
    if n == 1:
        return 1
    else:
        return n * factorial(n - 1)


def main():
    #for i in range(123456789,1234567890):
    #k = 1
    #k = 362881
    #k = 806401
    #for i in xrange(3019876543, 4019876543):
    #    if check_permutation(i) == 1:
    #        print k, " ", i
    #        k += 1


    #362880   0987654321
    #362881   1023456789
    #print factorial(10)

    #730800   2019876543
    #806400   2198765430
    #922319   3298765401
    out = itertools.permutations([1,2,3])

    print out.next()

    out2 = itertools.permutations([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    for i in range(1, 10**6):
        ls = out2.next()

    print ls
    print out2.next()
    #2783915406

if __name__ == '__main__':
    main()