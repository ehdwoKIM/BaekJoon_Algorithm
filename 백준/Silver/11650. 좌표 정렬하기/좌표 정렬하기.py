n=int(input())
lst=[]
for i in range(n):
    [a,b]=map(int, input().split())
    lst.append([a,b])
    
lst=sorted(lst)

for i in range(n):
    print(lst[i][0],lst[i][1])
