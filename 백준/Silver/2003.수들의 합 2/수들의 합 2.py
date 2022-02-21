n, m = map(int, input().split())
a = list(map(int, input().split()))

def solve():
    left = right = ans = s = 0
    while True:
        if s >= m:
            s -= a[left]
            left += 1
        else:
            if right == n:
                break
            s += a[right]
            right += 1
        if s == m:
            ans += 1
    return ans

print(solve())