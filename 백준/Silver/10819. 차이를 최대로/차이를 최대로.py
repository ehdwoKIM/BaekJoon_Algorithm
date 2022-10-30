import sys
from itertools import permutations

input = sys.stdin.readline
N = int(input().rstrip())
array = list(map(int, input().rstrip().split()))
length = len(array)

total_case = list(permutations(array, length))

def simulation(case):
    value = 0
    for i in range(1, len(case)):
        value += abs(case[i] - case[i-1])

    return value

answer = 0
for case in total_case:
    answer = max(answer, simulation(case))

print(answer)