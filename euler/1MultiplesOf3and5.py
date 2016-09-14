__author__ = 'ilya'

#If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
#Find the sum of all the multiples of 3 or 5 below 1000.
#https://projecteuler.net/problem=1

# for 1000 anwer 233168

i = 1
listEx = []

while i < 1000:
    print(i)
    if (i % 3 ==0) | (i % 5 == 0):
        listEx.append(i)
    i = i + 1

print(listEx)
Sum = sum(listEx)
print Sum