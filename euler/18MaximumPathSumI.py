__author__ = 'ilya'

input_list = 0
path_list = 0


def make_int(input_str):
    out_list = []
    for i in range(0, len(input_str)):
        tmp = []
        for j in range(0, len(input_str[i])):
            tmp.append(int(input_str[i][j]))
        out_list.append(tmp)
    return out_list


def read_input_long():
    input_list = []
    with open("files4tasks/triangle.txt") as f:
        content = f.readlines()
        print len(content)
        for i in range(0,len(content)):
            input_list.append(content[i].split())
            #print content[i].split()
        return make_int(input_list)


def read_input():
    input_list = [[75],
                  [95, 64],
                  [17, 47, 82],
                  [18, 35, 87, 10],
                  [20, 04, 82, 47, 65],
                  [19, 1, 23, 75, 3, 34],
                  [88, 02, 77, 73, 07, 63, 67],
                  [99, 65, 04, 28, 06, 16, 70, 92],
                  [41, 41, 26, 56, 83, 40, 80, 70, 33],
                  [41, 48, 72, 33, 47, 32, 37, 16, 94, 29],
                  [53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14],
                  [70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57],
                  [91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48],
                  [63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31],
                  [4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23]]
    return input_list


def make_clone(input_lt):
    out_list = []
    for i in range(0, len(input_lt)):
        tmp = []
        for j in range(0, len(input_lt[i])):
            tmp.append(0)
        out_list.append(tmp)
    return out_list


def move(x, y, sm):
    global input_list
    global path_list
    #print input_list[x][y]
    #print("x = " + str(x) +  " y = " + str(y))
    if sm > int(path_list[x][y]):
        path_list[x][y] = sm
        if x < len(input_list) - 1:
            k = x + 1
            h = y + 1
            new_sm = sm + input_list[x + 1][y]
            move(k, y, new_sm)
            move(k, h, sm + input_list[x + 1][y + 1])



def main():
    global input_list
    global path_list
    input_list = read_input()
    print len(input_list)
    path_list = make_clone(input_list)
    print path_list
    move(0, 0, 75)
    print path_list
    print max(path_list[len(path_list) - 1])

    #task2
    #print read_input_long()
    input_list = read_input_long()
    print input_list
    print len(input_list)
    path_list = make_clone(input_list)
    move(0, 0, 59)
    print path_list
    print max(path_list[len(path_list) - 1])

if __name__ == '__main__':
    main()