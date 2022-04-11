import re

N=int(input())

for _ in range(N):
    regex=re.compile('(A|B|C|D|E|F)?(A+)(F+)(C+)?(A|B|C|E|F)$')
    #re.compile 메서드 사용하여 패턴 객체를 반환
    Text=input()
    result=regex.match(Text)
    #반환된 패턴 객체에서 검색 메서드 사용하여 Text 찾는다
    if result:
        print("Infected!")
    else:
        print("Good")