#백준 집합 _ 비트마스킹으로 풀어보자
import sys

m=int(sys.stdin.readline())
check = [False for _ in range(21)]

for i in range(m):
    command=list(sys.stdin.readline().rstrip().split())
    cmd=command[0]
    
    if len(command) == 2:
        x = int(command[1])
        
    if cmd == 'add':
        if not check[x]:
            check[x] = True
            
    elif cmd == 'check':
        if check[x]:
            print(1)
        else:
            print(0)
            
    elif cmd == 'remove':
        if check[x]:
            check[x] = False
            
    elif cmd == 'toggle':
        if check[x]:
            check[x] = False
        else:
            check[x] = True
            
    elif cmd == 'all':
        check = [True for _ in range(21)]
    elif cmd == 'empty':
        check = [False for _ in range(21)]
    