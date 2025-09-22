import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws Exception{
        String s=new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        Deque<Integer> len=new ArrayDeque<>();
        Deque<Integer> mul=new ArrayDeque<>();
        len.push(0);
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(Character.isDigit(c) && i+1<s.length() && s.charAt(i+1)=='('){
                mul.push(c-'0');
                len.push(0); // 새 세그먼트
                i++; // '(' 넘김
            }else if(c==')'){
                int seg=len.pop();
                int k = mul.pop(); 
                len.push(len.pop() + k*seg);
            }else if(c=='('){
                
            }else{
                len.push(len.pop()+1);
            }
        }
        System.out.println(len.pop());
    }
}
