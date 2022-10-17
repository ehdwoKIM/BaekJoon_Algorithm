# 백준 2504 괄호의 값 (구현/자료구조/스택)

string = list(input())

class Stack:
    def __init__(self):
        self.len = 0
        self.list = []
        
    def push(self, x):
        self.list.append(x)
        self.len += 1
        
    def pop(self):
        if(self.size() == 0):
            return -1
        res = self.list[self.len - 1]
        del self.list[self.len - 1]
        self.len -= 1
        return res
    
    def size(self):
        return self.len
    
    def empty(self):
        return 1 if self.len == 0 else 0
    
    def top(self):
        return self.list[-1] if self.size() != 0 else -1
    
class customEx(BaseException): pass

stack = Stack()

if(string[0] == ')' or string[0] == ']'):
    print('0')
else:
    try:
        for str in string:
            if(str == '(' or str == '['):
                stack.push(str)
            elif(str == ')'):
                if(stack.top() == '('):
                    stack.pop()
                    stack.push(2)
                else:
                    res = 0
                    while(stack.top() != '('):
                        if(stack.top() != -1):
                            if(not isinstance(stack.top(), int)):
                                raise customEx
                            res += stack.top()
                            stack.pop()
                        else:
                            raise customEx
                    stack.pop()
                    res *= 2
                    stack.push(res)
            elif(str == ']'):
                if(stack.top() == '['):
                    stack.pop()
                    stack.push(3)
                else:
                    res = 0
                    while(stack.top() != '['):
                        if(stack.top() != -1):
                            if(not isinstance(stack.top(), int)):
                                raise customEx
                            res += stack.top()
                            stack.pop()
                        else:
                            raise customEx
                    stack.pop()
                    res *= 3
                    stack.push(res)          

        res = 0
        while(True):
            if(not stack.empty()):
                if(stack.top() != -1):
                    if(not isinstance(stack.top(), int)):
                        raise customEx
                    res += stack.top()
                    stack.pop()
            else:
                print(res)
                break
    except customEx:
        print(0)