__author__ = 'ilya'


class Base:
    def metthod(self):
        print("Hello!")


class Child(Base):
    pass
    def childMethod(self):
        print("this is child method!")

    def metthod(self):
        print("Hellow from ")


class Figure:
    def __init__(self, side=0.0):
        self.__class__ = side

    def check_instance(obj, cls):
        return cls in type(obj).mro()

    def check_subclass(child, base):
        return base in child.mro()


obj = Child()
obj.metthod()
obj.childMethod()

fg = Figure()
fg.check_instance(True, int)
