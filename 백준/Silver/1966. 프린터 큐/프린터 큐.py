import sys
from collections import deque

K = int(sys.stdin.readline())

for _ in range(K):
    N, M = map(int, sys.stdin.readline().split())
    queue = deque(list(map(int, sys.stdin.readline().split())))

    cnt = 0
    while True:
        max_prio = max(queue)
        temp = queue.popleft()
        if temp < max_prio:
            queue.append(temp)
        else:
            cnt += 1
            if M == 0:
                print(cnt)
                break
        M -= 1
        if M < 0:
            M = len(queue) - 1