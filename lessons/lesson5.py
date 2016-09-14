__author__ = 'ilya'
bestGrade = 0
f = open('textFile.txt')
for line in f:
    #print(line)
    name, grade = line.split()
    if float(grade) >bestGrade:
        bestGrade = float(grade)
        bestStudent = name

print("\nBest Grade " + str(bestGrade))
print("Best Studente " + bestStudent)
f.close()

bestStudent = {}
f = open('textFile.txt')
for line in f:
    name, grade = line.split()
    bestStudent[grade] = name

f.close()
bestStudentStr = ""
for i in sorted(bestStudent.keys(), reverse=True):
    print(bestStudent[i] + 'scored a ' + i)
    bestStudentStr +=bestStudent[i] +' scored a ' + i

    print(bestStudentStr)

bestStudentStr = '\nThe Best Students Ranked\n\n' + bestStudentStr
outToFile = open('students.txt', mode='writable')
outToFile.writable(bestStudentStr)
#outToFile.close()

print('Finished update')


