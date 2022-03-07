import sys
input = sys.stdin.readline

n,m = map(int,input().split())
trees = list(map(int,input().split()))
trees.sort()
end = trees[-1]

def bs(start, end):
    if start == end:
        return start
    
    mid = (start+end)//2
    total = 0
    for i in range(n-1,-1,-1):
        if trees[i] >= mid:
            total += trees[i] - mid
        else:
            break
            # 이미 전 나무에서 m 이상의 부분만큼 잘랐다면 
            # 앞의 나무는 생략해도 된다.

    if total >= m:
        start = mid
    else:
        end = mid
    
    # 더이상 이분 탐색을 실행할 수 없음
    if end-start == 1:
        return start
        
    return bs(start, end)

print(bs(0,end))