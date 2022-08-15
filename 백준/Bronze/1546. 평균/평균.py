N=int(input())
score=list(map(int,input().split()))
a = 0
for i in range(N):
    a = a + score[i]
b = (a / N) / max(score) * 100 

print(b)
