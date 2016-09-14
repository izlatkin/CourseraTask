__author__ = 'ilya'

def multiplyTwo(var1 = 1, var2 = 1, *args):
    print(str(var1) + " time 2 is {}".format(var1*2))
    print(str(var2) + " time 2 is {}".format(var2*2))

    listEx = [var1 * 2,var2 * 2]

    print(listEx)

    for i in args:
        listEx.append(i * 2)

    return listEx


def howOld(age):
    if age > 35:
        return "Your Older than me"
    elif age < 35:
        return "Your Younger than Me"
    else: return "Your as Old as Me"

def animal(yourAnimal):
    animalPref = 'Same as me ' if (yourAnimal == 'Cat') else 'Booooo'
    return animalPref

def divideNStuff(x, y):
    try:
        divResult = x/y
    except ZeroDivisionError:
        print("ZeroDivisionError")
    except TypeError as e:
        print("Not enought arguments")
    except Exception:
        print("Something wrong")
    else:
        print(str(x) + " divied by" + str(y) + " equals " + str(divResult))

def main():
    doubles = multiplyTwo(1,2,3,4,5)

    for i in doubles:
        print(i)

    print(howOld(35))
    print(animal('Dog'))
    print(animal('Cat'))
    firstVar = input("Enter First Num: ")
    seconfVar = input("Enter Second Num: ")
    divideNStuff(int(firstVar),int(seconfVar))


if __name__ == '__main__':main()


