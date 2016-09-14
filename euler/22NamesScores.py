__author__ = 'ilya'

# https://projecteuler.net/problem=22
# anser = 871198282

def read_input(fname):
    with open(fname) as f:
        content = f.readlines()
        return content


def name_score(name):
    sm = 0
    for i in range(0, len(name)):
        sm += ord(name[i]) - ord('A') + 1
        #print(name[i] + " " + str(ord(name[i]) - ord('A')))
    print(name + " " + str(sm))
    return sm


def main():
    content = read_input("files4tasks/p022_names.txt")
    print content[0]
    splittedline = str(content[0]).split(",")
    print str(content).split("\",\"")
    lenght = len(splittedline)
    print sorted(splittedline)[937]
    print splittedline[0][1:-1]
    name_score(splittedline[937][1:-1])
    sorted_splited_lene = sorted(splittedline)
    #sorted_splited_lene = splittedline
    print sorted_splited_lene[937][1:-1]
    sm = 0
    for i in range(0, lenght):
        print str(i + 1)
        sm += (i + 1) * name_score(sorted_splited_lene[i][1:-1])
    print sm

    name_score("COLIN")

if __name__ == '__main__':
    main()