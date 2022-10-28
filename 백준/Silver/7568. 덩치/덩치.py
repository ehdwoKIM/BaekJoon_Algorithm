def solution(n, people):
    for i in range(n):
        for j in range(i + 1, n):
            if people[i][0] > people[j][0] and people[i][1] > people[j][1]:
                people[j][2] += 1
            elif people[i][0] < people[j][0] and people[i][1] < people[j][1]:
                people[i][2] += 1
    for i in range(n):
        print(people[i][2] + 1, end=' ')


N = int(input())
People = [0] * N
for i in range(N):
    weight, height = map(int, input().split())
    People[i] = [weight, height, 0]
solution(N, People)