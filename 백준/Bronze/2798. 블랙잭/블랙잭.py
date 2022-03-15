# 백준 블랙잭
from itertools import combinations

n, m = map(int, input().split())
lst = list(map(int, input().split()))

lst2 = list(combinations(lst, 3))

ans=[]
for i in lst2:
    if sum(i)<=m:
        ans.append(sum(i))
print(max(ans))
