__author__ = 'ilya'

# Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
# Answer = 5537376230
# 55373762303908766373020487468329859717736598318926720


def read_input(fname):
    with open(fname) as f:
        content = f.readlines()
        return content


def main():
    content = read_input("files4tasks/13input")
    print(len(content))
    print(content[0][1])
    print(content[3][1])
    print
    summ = 0
    for j in range(0, 50):
        for i in range(0, 100):
            print(long(content[i][j]))
            summ += long(content[i][j])
        summ *= 10
        print
        print summ
        print
        
    print str(summ)[:10]
    print str(summ)
    print type(summ)

if __name__ == '__main__':
    main()
