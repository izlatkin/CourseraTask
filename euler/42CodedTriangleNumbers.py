__author__ = 'ilya'


def open_input(fname):
    with open(fname) as f:
        content = f.readlines()
        return content

def read_input_file():
    content = open_input("files4tasks/p042_words.txt")
    print content[0]
    splittedline = str(content[0]).split(",")
    out = str(content).split("\",\"")
    print out
    return out


def main():
    read_input_file()


if __name__ == '__main__':
    main()