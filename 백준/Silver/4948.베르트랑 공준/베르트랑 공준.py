def isPrime(num):
    if num==1:
        return False
    else:
        for i in range(2, int(num**0.5)+1):
            if num%i == 0:
                return False
        return True
    
all_lst=list(range(2,246912)) #문제에서 제한한 범위
memo=[]

for i in all_lst:
    if isPrime(i):
        memo.append(i)

n=int(input())

while True:
    cnt=0
    if n==0:
        break
    for i in memo:
        if n < i <= 2*n:
            cnt+=1
    
    print(cnt)
    n = int(input()) #0 입력받기 전까지 계속 해야하므로 입력받음