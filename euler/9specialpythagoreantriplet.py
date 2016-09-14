__author__ = 'ilya'

# coding=utf-8


# A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
#    a^2 + b^2 = c^2
# For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
# There exists exactly one Pythagorean triplet for which a + b + c = 1000.
# Find the product abc.

# answer = 31875000

def compare(a, b, c):
    if a**2 + b**2 == c**2:
        return True
    else:
        False

def main():
    s = compare(3,4,5)
    print(s)

    for c in range(333, 1000):
        for b in range(1, 1000 - c):
            a = 1000 - c - b
            if compare(a, b, c):
                print("a = " + str(a) + " b = " + str(b) + " c = " + str(c))
                print(str(a * b * c))
                break

if __name__ == '__main__':
    main()

#    180625
#    140625
#     40000