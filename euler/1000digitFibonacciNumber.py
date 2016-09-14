__author__ = 'ilya'


#ansewr 4782

i = 1
fibonacci = [1, 2, 3]
fibonacciEven = [2]
#print(len(fibonacci))

while i < 5000:
    #i = fibonacci[len(fibonacci) - 1];
    print(i)
    i = i +1
    nextNum = fibonacci[len(fibonacci) - 2] + fibonacci[len(fibonacci) - 1]
    fibonacci.append(nextNum)
    if (nextNum > 10 ** 1000):
        break
    if nextNum % 2 == 0:
        fibonacciEven.append(nextNum)

print fibonacci[len(fibonacci) - 2]
print len(str(fibonacci[len(fibonacci) - 2]))

print fibonacci[len(fibonacci) - 1]
print len(str(fibonacci[len(fibonacci) - 1]))