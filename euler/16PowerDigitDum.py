__author__ = 'ilya'


# 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
# What is the sum of the digits of the number 2^1000?


# 1366

def main():
    num_str = str(2 ** 1000)
    print(num_str)
    num_list = []
    print num_str.__sizeof__()
    i = 329
    #print int(num_str[int(i - 1):i])
    for i in range(1, len(num_str)):
        #print int(num_str[i - 1:i])
        num_list.append(int(num_str[i - 1:i]))

    #print num_list
    print  num_list
    print sum(num_list) + 6

if __name__ == '__main__':
    main()