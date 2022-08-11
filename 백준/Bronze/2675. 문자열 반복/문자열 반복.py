num=int(input())
result=str()
for i in range(num):
  num_of_time, test_str=input().split()
  num_of_time=int(num_of_time)
  for j in test_str:
    result=result+(j*num_of_time)
  print(result)
  result=str()
