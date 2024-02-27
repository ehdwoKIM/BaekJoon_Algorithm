# 백준 15686 치킨배달

from itertools import combinations

#N, M 입력
N, M = map(int, input().split())

# NxN 도시 정보 입력
cityMap = []
for i in range(N):
    row = list(map(int, input().split()))
    cityMap.append(row)
  
# 집과 치킨집 좌표 저장  
houses = [(i, j) for i in range(N) for j in range(N) if cityMap[i][j] == 1]
chickens =[(i, j) for i in range(N) for j in range(N) if cityMap[i][j] == 2]

def calculate_distance(house, chicken):
    return abs(house[0] - chicken[0]) + abs(house[1] - chicken[1])

#치킨 거리 계산
def calculate_chicken_distance(houses, chickens, m):
    min_distance = float('inf')
    for comb in combinations(chickens, m):
        distance = 0
        for house in houses:
            distance += min(calculate_distance(house, chicken) for chicken in comb)
        min_distance = min(min_distance, distance)
    return min_distance

# 최소 치킨 거리
min_chicken_distance = calculate_chicken_distance(houses, chickens, M)
print(min_chicken_distance)


    
    