__author__ = 'ilya'


# ans = 9183

def main():
    lst = []
    for a in range(2, 101):
        for b in range(2, 101):
            current = a ** b
            if current not in lst:
                lst.append(current)
    print sorted(lst)
    print len(lst)


if __name__ == '__main__':
    main()