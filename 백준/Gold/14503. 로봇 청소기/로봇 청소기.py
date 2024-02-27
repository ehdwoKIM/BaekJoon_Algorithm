# 백준 14503 로봇 청소기

def turn_left(d):
    return (d - 1) % 4

def cleaning(cleanMap, r, c, d, N, M):
    dr = [-1, 0, 1, 0]  # 북, 동, 남, 서
    dc = [0, 1, 0, -1]
    
    count = 0  # 청소하는 칸의 수
    turns = 0  # 회전한 횟수
    
    while True:
        if cleanMap[r][c] == 0:  # 현재 위치 청소
            cleanMap[r][c] = 2
            count += 1
        
        d = turn_left(d)  # 왼쪽으로 회전
        nr, nc = r + dr[d], c + dc[d]
        
        if 0 <= nr < N and 0 <= nc < M and cleanMap[nr][nc] == 0:  # 청소할 수 있는 공간이 있으면 이동
            r, c = nr, nc
            turns = 0
            continue
        else:  # 이동할 수 없으면 회전만 수행
            turns += 1
        
        if turns == 4:  # 네 방향 모두 청소되었거나 벽인 경우
            nr, nc = r - dr[d], c - dc[d]  # 후진
            if 0 <= nr < N and 0 <= nc < M and cleanMap[nr][nc] != 1:  # 후진할 수 있으면 후진
                r, c = nr, nc
                turns = 0
            else:  # 후진도 불가능하면 작업 종료
                break
                
    return count

N, M = map(int, input().split())
r, c, d = map(int, input().split())
cleanMap = [list(map(int, input().split())) for _ in range(N)]

print(cleaning(cleanMap, r, c, d, N, M))
