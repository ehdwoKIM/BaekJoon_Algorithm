N=int(input())

for i in range(N):
  score=0
  Total_score=0
  a=input()
  for b in a:
    if b=='O':
      score=score+1
      Total_score=Total_score+score
    else:
      score=0
  print(Total_score)