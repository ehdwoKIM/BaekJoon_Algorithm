import sys
from collections import deque
from itertools import combinations
import copy
input = sys.stdin.readline
n, m = map(int, input().split())
graph = []
blank = []
virus = []
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def bfs(v, copy_graph):
    visited = [[False]*m for _ in range(n)]
    for k in v:
        q = deque()
        q.append((k[0], k[1]))
        visited[k[0]][k[1]] = True
        while q:
            x, y = q.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < n and 0 <= ny < m:
                    if copy_graph[nx][ny] == 1:
                        visited[nx][ny] = True
                        continue
                    elif graph[nx][ny] == 0:
                        if visited[nx][ny] == False:
                            copy_graph[nx][ny] = 2
                            visited[nx][ny] = True
                            q.append((nx, ny))
    count = 0
    for i in range(n):
        for j in range(m):
            if copy_graph[i][j] == 0:
                count += 1
    return count
for i in range(n):
    data = list(map(int, input().split()))
    for j in range(m):
        if data[j] == 0:
            blank.append([i, j])
        elif data[j] == 2:
            virus.append([i, j])
    graph.append(data)
max_count = 0
for combi in list(combinations(blank, 3)):
    copy_graph = copy.deepcopy(graph)
    for c in combi:
        x, y = c
        copy_graph[x][y] = 1
    count = bfs(virus, copy_graph)
    max_count = max(max_count, count)
print(max_count)