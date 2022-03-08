# 백준 수 찾기
import sys

n=int(sys.stdin.readline())
A=[int(x) for x in sys.stdin.readline().split()]
m=int(sys.stdin.readline())
B=[int(y) for y in sys.stdin.readline().split()]

for i in range(m):
    if B[i] in A:
        print("1")
    else:
        print("0")    