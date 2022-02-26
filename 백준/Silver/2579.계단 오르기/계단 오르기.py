import sys

N = int(sys.stdin.readline())
stair = []

for i in range(N):
	stair.append(int(sys.stdin.readline()))

if N < 3:
	print(sum(stair))
else:
	dp = [0] * N
	dp[0] = stair[0]
	dp[1] = stair[0] + stair[1]
	dp[2] = max(stair[0]+stair[2], stair[1]+stair[2])
    #for문 밖에서 수식에 i-3이 들어가므로 인덱스 범위를 지키기 위해 dp에 값을 넣어준다
	for i in range(3,N):
	    dp[i] = max(stair[i]+dp[i-2], stair[i]+stair[i-1]+dp[i-3])

	print(dp[-1])    