import getopt
import os
import shutil, errno
import sys
import platform


def copyanything(src, dst):
    try:
        shutil.copytree(src, dst)
    except OSError as exc:
        if exc.errno == errno.ENOTDIR:
            shutil.copy(src, dst)
        else:
            raise


jck_path = ''
work_dir = ''
try:
    opts, args = getopt.getopt(sys.argv[1:], "hj:w:", ["jck_path=", "work_dir="])
except getopt.GetoptError:
    print("test.py -j <jck_path> -w <work_dir>")
    sys.exit(2)
for opt, arg in opts:
    if opt == '-h':
        print('test.py -j <jck_path> -w <work_dir>')
        sys.exit()
    elif opt in ("-j", "--jck_path"):
        jck_path = arg
    elif opt in ("-w", "--work_dir"):
        work_dir = arg
print("jck_path is ")
print(jck_path)
print("work_dir is ")
print(work_dir)

if jck_path == '':
    jck = "/set/java/re/jck/9/qac/b25-2015-11-09/binaries/JCK-runtime-9"
else:
    jck = jck_path
jck_add = jck.replace("/set/java/re/jck/9", "/net/zenit.ru.oracle.com/export6/java/re/jck/9")

if work_dir == '':
    work_dir = "/net/jessika/export/qa-results/jck/9/ea/b25/results/qac/1426"

directory = work_dir[work_dir.rfind("/") + 1:]
if platform.system() == "Windows":
    directory = work_dir[directory.rfind("\\") + 1:]
work_dir_add1 = work_dir.replace("jessika", "jessika.ru.oracle.com")
work_dir_add2 = work_dir.replace("/net/jessika/export/qa-results", "/net/spbnas402/export/SQE_jessika/qa-results")
work_dir_add3 = work_dir.replace("/net/jessika/export/qa-results", "/net/spbnas402.ru.oracle.com/export/SQE_jessika/qa-results")

print("OS")
print(platform.system())

jplis = "/net/jessika/export/tools/tmp/QATools2/jplis.jar"
if platform.system() == "Windows":
    jplis = "W:/tools/tmp/QATools2/jplis.jar"
jplis_classes = "/tests/api/java_lang/instrument/Instrumentation"
javax_management_classes = "/tests/api/javax_management/loading/data"
print("directory:")
print(directory)
if not os.path.exists(directory):
    os.makedirs(directory)
else:
    print("dir already exist")

copyanything(jck + "/lib", directory + "/out/lib")
copyanything(jplis, directory + "/jplis.jar")
copyanything(jck + "/modules", directory + "/out/modules")
copyanything(jck + jplis_classes, directory + "/out" + jplis_classes)
copyanything(jck + javax_management_classes, directory + "/out" + javax_management_classes)

try:
    file = open(directory + "/map.txt", 'w')
    addition = " " + os.getcwd() + "/" + directory + "/out\n"
    file.write(jck + addition)
    file.write(jck_add + addition)
    file.write(work_dir + addition)
    file.write(work_dir_add1 + addition)
    file.write(work_dir_add2 + addition)
    file.write(work_dir_add3 + addition)
    file.close()
except:
    print('impossible to write in file')
    sys.exit(0)
