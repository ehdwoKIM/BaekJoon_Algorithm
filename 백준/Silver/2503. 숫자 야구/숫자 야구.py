import sys

sequence = [(i, j, k)
            for i in range(1, 10)
            for j in range(1, 10)
            for k in range(1, 10)
            if i!=j and j!=k and i!=k]

n=int(sys.stdin.readline().rstrip())
num_lst=[]
strike_lst=[]
ball_lst=[]
for i in range(n):
    num, strike, ball = map(int, sys.stdin.readline().split())
    
    num_lst.append(list(map(int, list(str(num)))))  
    strike_lst.append(strike)
    ball_lst.append(ball)
    
ans=0

for x, y, z in sequence:
    for i in range(n):
        strike_cnt = 0
        ball_cnt = 0
        # 같은 위치에 값도 같으면 strike
        # 다른 위치에 있으면 ball
        if num_lst[i][0] == x:
            strike_cnt+=1
        else:
            if num_lst[i][0] == y or num_lst[i][0] == z:
                ball_cnt+=1

        if num_lst[i][1]==y:
            strike_cnt+=1
        else:
            if num_lst[i][1]==x or num_lst[i][1]==z:
                ball_cnt+=1
        
        if num_lst[i][2]==z:
            strike_cnt+=1
        else:
            if num_lst[i][2]==y or num_lst[i][2]==x:
                ball_cnt+=1
        
        if (strike_cnt != strike_lst[i]) or (ball_cnt != ball_lst[i]):
            break
        
    else:
        ans+=1
        
print(ans)