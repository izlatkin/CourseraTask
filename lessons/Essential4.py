__author__ = 'ilya'


for x in range(5):
    print(x)

print range(0,5)
print type(range(5))
y =  range(5)
print y.__iter__()
iter = y.__iter__()
values = [1,2,3,4,5]
#values.__getitem__(5)
try:
    print next(iter)
    print next(iter)
    print next(iter)
    print next(iter)
    print next(iter)
    print next(iter)
except StopIteration:
    print "Was chatched"


def my_for(iterable, callback):
    #it = iter(iterable)
    it = iterable.__iter__()

    while True:
        try:
            value = next(it)
            callback(value)
        except StopIteration:
            break


def loop_body(value):
    print('Next value recieved: ', value)


my_for(range(10), loop_body(5))

