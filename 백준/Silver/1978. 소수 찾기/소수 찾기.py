n = input()
n = int(n)
numbers = map(int, input().split())
cnt=0

for i in numbers:    
   error=0
   if i>1 :
        for j in range(2, i):
           if i%j==0:
               error+=1 # 2부터 n-1까지 나눈 몫이 0이면 error
        if error==0:
            cnt+=1    
print(cnt)            