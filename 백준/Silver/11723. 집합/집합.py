#백준 집합
import sys

m=int(sys.stdin.readline())
s=set()

for i in range(m):
    command=sys.stdin.readline().strip().split()
    
    if len(command)==1:
        if command[0]=='all':
            s=set([i for i in range(1, 21)])
        elif command[0]=='empty':
            s=set()
    
    else:
        com, x = command[0], command[1]
        x=int(x)
        
        if com=='add':
            s.add(x)
        elif com=='remove':
            s.discard(x) #remove함수는 존재하지 않는 수를 제거하려고 하면 오류를 발생하는데 discard함수를 사용하면 오류가 나지않고 정상종료
        elif com=='check':
            if x in s:
                print(1)
            else:
                print(0)
        elif com=='toggle':
            if x in s:
                s.discard(x)
            else:
                s.add(x)
            
    