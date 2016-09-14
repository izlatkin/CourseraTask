__author__ = 'ilya'


def isPalidrome(string_n):
    if len(string_n) == 1:
        return True
    for i in range(0, len(string_n) / 2):
        return string_n[::-1] == string_n
        #if (string_n[i] != string_n[::-i]):
        #    return False



def main():
    #print isPalidrome(str(7117))
    #print(bin(777)[2:])
    #print str(bin(10))
    ls = []
    for i in range(1, 999999):
        if isPalidrome(str(i)) & isPalidrome(str(bin(i)[2:])):
            print i, bin(i)[2:]
            ls.append(i)
    print(ls)
    print(sum(ls))

if __name__ == '__main__':
    main()
