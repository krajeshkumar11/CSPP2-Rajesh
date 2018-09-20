from collections import defaultdict
import math
def readAfile():
	try:
		with open(input("enter input file name   "), 'r') as myfile:
			words=myfile.read().replace('\n', ' ')
		return words.lower()
	except:
		print("enter correct file name")
		readAfile()



def makedict(words):
	d = defaultdict(int)
	for word in words.split():
		flag = False
		for i in word:
			if (ord(i)>=97 and ord(i)<=122) or (ord(i)>=48 and ord(i)<=57) or ord(i)==95:
				pass
			else:
				flag = True
				break
		if flag==False:
			d[word] += 1
	return d

def percentageMatch(a,b):
	 file1 = math.sqrt(sum(map(lambda x:x*x,a.values())))
	 file2 = math.sqrt(sum(map(lambda x:x*x,b.values())))
	 total = 0
	 for keys in a:
	 	if keys in b:
	 		total +=  a.get(keys) * b.get(keys)

	 print ((total/(file1*file2))*100)




d1 = readAfile()
d2 = readAfile()
a = makedict(d1)
b = makedict(d2)
percentageMatch(a,b)

# a = eval(input())
# print (a)
