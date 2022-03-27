#백준 달팽이

n = int(input())
idx = int(input())
arr = [[0]*n for _ in range(n)]
start_num = n*n

# 하 우 상 좌
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
x = 0
y = 0
# 0 : 하, 1: 우, 2: 상, 3:좌
direction = 0
while True:
    if start_num==0:
        break
        
    arr[x][y] = start_num
    nx = x + dx[direction]
    ny = y + dy[direction]
    if nx  >= n or  nx < -1 or ny >= n or ny < -1 or arr[nx][ny] != 0:
        if direction == 3:
            direction = 0
        else:
            direction = direction + 1
    x = x + dx[direction]
    y = y + dy[direction]
    start_num = start_num -1

for i in range(n):
    if idx in arr[i]:
        ky = arr[i].index(idx)
        kx = i
    for j in range(n):
        print(arr[i][j], end = ' ')
    print()
print(kx+1, ky+1)
   