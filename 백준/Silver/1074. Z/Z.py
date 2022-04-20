import sys
N, r, c = map(int, sys.stdin.readline().split())
def recur(N, r, c, num):
    if N == -1:
        print(num)
        return
    if c < ((2 ** N) / 2):
        if r < ((2 ** N) / 2):
            # print("1사분면")
            pass
        else:
            # print("3사분면")
            num += (2**(2*N - 2) * 2)
            r -= ((2 ** N) // 2)
    else:
        if r < ((2 ** N) // 2):
            # print("2사분면")
            num += (2**(2*N - 2))
            c -= ((2 ** N) // 2)
        else:
            # print("4분면")
            num += (2**(2*N - 2) * 3)
            r -= ((2 ** N) // 2)
            c -= ((2 ** N) // 2)
    recur(N - 1, r, c, num)
recur(N, r, c, 0)