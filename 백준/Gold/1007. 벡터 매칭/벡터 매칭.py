import sys
import math
import itertools
# import random
T = int(sys.stdin.readline())
results = []
 
for _ in range(T):
    N = int(sys.stdin.readline())
    coords = []
    
    # 모든 좌표들의 총합
    x_total = 0
    y_total = 0
    for _ in range(N):
        x, y = map(int, sys.stdin.readline().split())
        x_total += x
        y_total += y
        coords.append([x, y])
    
    res = math.inf
    combinations = list(itertools.combinations(coords, int(N/2)))
    combinations_len_half = int(len(combinations) / 2)
    for sum_coord in combinations[:combinations_len_half]:
        sum_coord = list(sum_coord)
 
		# 더해야하는 좌표 총합
        x1_total = 0
        y1_total = 0
        for x1, y1 in sum_coord:
            x1_total += x1
            y1_total += y1
        
        # 빼야하는 좌표 총합(= 모든 좌표들의 총합 - 더해야하는 좌표 총합)
        x2_total = x_total - x1_total
        y2_total = y_total - y1_total
        
        res = min(res, math.sqrt((x1_total - x2_total) ** 2 + (y1_total - y2_total) ** 2))
 
    results.append(res)
 
for result in results:
    sys.stdout.write(str(result)+'\n')