__author__ = 'ilya'
#String example

stringVar = "This is a samle string"
print(stringVar)

numOne = 2
wordOne = "Cats"

print("I have " + str(numOne)+ " " + wordOne)

combineStr = "I have " + str(numOne)+ " " + wordOne
combineStr += " I think? "

print(combineStr)

stringVar2 = "Just another string"

for i in stringVar2:
    print(i)

print("\nThe first letter in the sting is " + stringVar2[0])

#for i in stringVar2:
#        print(i, end="")

string3 = """\n This is a \
multiple sting """
print(string3)

string4 = "This some really long strings here"
print(string4)
print(string4.find('really'))

print(string4[13:19])

string4 = string4.replace('e','a')

print(string4)


string5 = "     this is long    string    with a   lot   of spaces       "
string5 = string5.strip()
print(string5)


string5 = string5.split()
print(type(string5))

print(string5)
string5 = ' '.join(string5)
print(len(string5))

string6 = r"I don't want want to "
print(string6)

