n=int(input())

lst=[]
for i in range(n):
    lst.append(int(input()))

for i in sorted(lst):
    print(i)