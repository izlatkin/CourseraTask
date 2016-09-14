__author__ = 'ilya'

class Animal:
#    def __init__(self, name = 'No Name'):
#        self._name = name

    def __init__(self, **kwargs):
            self._attributes = kwargs

    def set_attributes(self, key, value):
        self._attributes[key] = value

    def get_attributes(self, key):
        return self._attributes.get(key, None)

    def noise(self):
        print("Errrrrr")

    def move(self):
        print("Moving Forward")

    def eat(self):
        print("Crunch , Crunch")


class Dog(Animal):
    def noise(self):
        print("Woof, Woof")
       # super(Dog, self).noise(Animal)

class Cat(Animal):
    def noise(self):
        print("Meow")

def tackToMe(Animal):
    Animal.noise()
    Animal.eat()

#dog = Animal(name = "Puppy")
#dog.noise()
#dog.move()
#dog.eat()
#print(dog.get_attributes('name'))
#dog.set_attributes('Feet', 3)
#print(dog.get_attributes('Feet'))

jake = Dog()
print(jake.noise())
print(jake.move())

sophie = Cat()
an = Animal()
tackToMe(sophie)

