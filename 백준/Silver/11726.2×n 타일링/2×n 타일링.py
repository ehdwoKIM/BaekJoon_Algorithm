n=int(input())
dp = [0]*1001
dp[1] = 1
dp[2] = 2
ans=0

for i in range(3,1001):
    dp[i]=dp[i-1]+dp[i-2]
    
ans=dp[n] % 10007    
print(ans)