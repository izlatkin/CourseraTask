#!/usr/bin/python

import sys, getopt

def main(argv):
    jck_path = ''
    work_dir = ''
    try:
        opts, args = getopt.getopt(argv,"hj:w:",["jck_path=","work_dir="])
    except getopt.GetoptError:
        print 'test.py -j <jck_path> -w <work_dir>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'test.py -j <jck_path> -w <work_dir>'
            sys.exit()
        elif opt in ("-j", "--jck_path"):
            jck_path = arg
        elif opt in ("-w", "--work_dir"):
            work_dir = arg
    print 'jck_path is ', jck_path
    print 'work_dir is ', work_dir
    print work_dir[work_dir.rfind("/") + 1:]

if __name__ == "__main__":
    main(sys.argv[1:])
