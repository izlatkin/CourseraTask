__author__ = 'ilya'

class MyObject:
    def __del__(self):
        print(self, 'is about to be deleted')
        Exception


obj = MyObject()        # this ob is stored is the heap - obj is a link in to the heap
del obj
obj = MyObject()  # when this is no link to the ob, next garbege collectore will del this obj, as much fast
# er as it possible
print id(obj)
ref = obj
print(id(ref))
del obj
del ref
