__author__ = 'ilya'


# anser = 443839

def main():
    lst = []
    for a in range(2, 9999999):
        st = str(a)
        #print st
        ssum = 0
        for i in range(0, len(st)):
           #print st[i]
           ssum += int(st[i]) ** 5

        if ssum == a:
            lst.append(a)
            print ssum

    print sorted(lst)
    print len(lst)
    print sum(lst)


if __name__ == '__main__':
    main()