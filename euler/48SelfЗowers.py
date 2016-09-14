__author__ = 'ilya'

#Congratulations, the answer you gave to problem 48 is correct.
#You are the 77730th person to have solved this problem.
#9110846700



def cut_extra(n):
    if len(str(n)) > 10:
        return int(str(n)[-10:])
    else: return n


def self_power(n):
    #print cut_extra(n)
    result = n
    for i in range(1, n):
        result *= n
        result = cut_extra(result)
    return result


def self_self_power(n):
    result = 0
    for i in range(1, n):
        result += self_power(i)
        result = cut_extra(result)
    print result


def main():
    self_self_power(1000)


if __name__ == '__main__':
    main()
