__author__ = 'ilya'


# 73682

var = 0


def sum_update(ssum, st, list_of_coins):
    global var
    if ssum == 200:
        print st
        var += 1
        return 0
    if ssum > 200:
        return 0
    if ssum < 200:
        for i in range(0, len(list_of_coins)):
            new_st = st + "+" + str(list_of_coins[i])
            new_ssum = ssum + list_of_coins[i]
            sum_update(new_ssum, new_st, list_of_coins[i:])


def main():
    global var
    lst = [1, 2, 5, 10, 20, 50, 100, 200]
    sum_update(0, " ", lst)
    print var


if __name__ == '__main__':
    main()