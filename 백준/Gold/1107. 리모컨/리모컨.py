# 백준 리모컨
#------------
#- 어떤 수를 기준으로 반복할 것이냐가 포인트인데 string으로 target 숫자를 관찰하며 시뮬레이션 할 수도 있지만 이 문제는 brute force를 사용하였다. 
# 조건이 500000이므로 최대 1,000,000까지 관찰하며 번호를 누른 후 이동할 수 있는 모든 경우를 계산한다. 
# if문으로 상황을 쳐내려가는게 아니라 숫자 자체를 관찰하므로 고장난 버튼이 들어잇는 경우는 무조건 제거하고 전부 가능한 버튼들이라면 min_cnt를 갱신하며 반복한다.
from sys import stdin
input = stdin.readline

target = input().strip() # 가고 싶은 채널
broken_num = int(input()) # 고장난 채널 개수
broken = list(map(int, input().split())) # 고장난 채널 번호

button = [i for i in range(0,10) if i not in broken] # 고장나지 않은 채널 번호
# +, - 로만 채널 이동하는 경우
min_cnt = abs(100-int(target))
# 숫자로 이동하는 경우
for num in range(1000000):
    num = str(num)
    for i in range(len(num)):
        if int(num[i]) not in button: break
        elif i == len(num)-1:
            min_cnt = min(min_cnt, len(num)+abs(int(num)-int(target)))
print(min_cnt)
 