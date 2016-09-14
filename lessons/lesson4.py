# immutable integer
# https://www.youtube.com/watch?v=YiLiCxq1QX8&list=PL3072C720775B213E&index=4
__author__ = 'ilya.zlatkin@oracle.com'

intEx = 1
print(id(intEx))
intEx = 2
print(id(intEx))

# mutable list
listEx = ['Derek']
print((id(listEx)))
listEx.append('Cat')
print((id(listEx)))

tupleEx = ('Derek',35,'St-Petersburg', 'PA')
for i in tupleEx:
    print(i)

print("first element in list is " + tupleEx[0])

listEx = ['Derek',35,'St-Petersburg', 'PA']
for i in listEx:
    print(i)

listEx.append("Joy")
print(listEx[4])
listEx.remove(listEx[3])
listEx.insert(2,'PA')
listEx.sort()
print(listEx)
listEx.reverse()
print(listEx)
listEx2 =[
    ['a','b','c'],
    ['d','f','g'],
    ['h','j','k'],
]
print(listEx2[2][1])

#dictionary
dictEx = ({"Age":29,"Height":"176","Weight":66.6})
print(dictEx)
print(dictEx.get("Height"))
print(dictEx.items())
print(dictEx.values())
dictEx.pop("Height")
print(dictEx)
a, b = 1, 11
while a < b:
    print(a)
    a += 1

for x in [1,2,3,4]:
    print(x)

listCycle = [1,2,3,4]
listCycle[:] = range(1,201)
#for i in listCycle: print(i)

for i in listCycle:
    if(i%2) == 0:
        continue
    elif (i == 101):
        break
    else:
        print(i)

