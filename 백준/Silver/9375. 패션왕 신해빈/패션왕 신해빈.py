test_case = int(input())

for _ in range(test_case):
    n = int(input())
    clothe_type = {}
    for _ in range(n):
        _, tmp = input().split()
        if tmp in clothe_type:
            clothe_type[tmp] += 1
        else:
            clothe_type[tmp] = 1
    result = 1
    for key in clothe_type:
        result *= (clothe_type[key] + 1)
    print(result - 1)