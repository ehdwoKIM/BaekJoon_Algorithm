# 백준 2374

n=int(input())
stack=[]
cnt=0
m=0

for i in range(n):
    x=int(input())
    m=max(m,x)
    
    if stack:
        if stack[-1] < x:
           cnt += x - stack.pop()
           stack.append(x)
        else:
            stack.pop()
            stack.append(x)
    else:
        stack.append(x)
while stack:
    cnt+=m-stack.pop()
print(cnt) 