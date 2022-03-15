#백준 나이순 정렬
import sys
input=sys.stdin.readline
n=int(input())

lst=[]
for i in range(n):
    lst.append(list(input().split()))

#나이 기준 정렬
lst.sort(key=lambda a:int(a[0]))

for i in range(n):
    print(lst[i][0], lst[i][1])





