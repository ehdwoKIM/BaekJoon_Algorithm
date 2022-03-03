import sys
word=set() # 중복고려
for i in range(int(input())):
    word.add(sys.stdin.readline().rstrip())
word=sorted(list(word)) #리스트로 바꿔주면서 오름차순 정렬 (사전순)
word.sort(key=lambda x:len(x)) #오름차순 정렬(길이대로)

for i in range(len(word)):
    print(word[i])
