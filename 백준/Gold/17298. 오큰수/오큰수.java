import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n]; // 수열 배열
        int[] ans = new int[n]; // 정답 배열 생성
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        Stack<Integer> myStack = new Stack<>();
        myStack.push(0); // 처음 스택이 비어있으니 최초 값을 초기화
        for (int i = 1; i < n; i++) {
            // 스택이 비어있지 않고 현재 수열이 스택 top 값보다 클 때
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                ans[myStack.pop()] = A[i]; // 정답 배열에 오큰수 저장
            }
            myStack.push(i); // 신규 데이터 push
        }
        while (!myStack.isEmpty()) {
            // 반복문 다 돌고 나왔는데 스택이 empty가 아닐 경우 빌 때까지
            ans[myStack.pop()] = -1;
            // stack에 쌓은 인덱스에 -1 저장
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.write("\n");
        bw.flush();
    }
}
