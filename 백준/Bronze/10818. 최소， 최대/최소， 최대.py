n=int(input())
y=input().split()
list=[]
for i in y:
  list.append(int(i))
print(min(list),max(list))