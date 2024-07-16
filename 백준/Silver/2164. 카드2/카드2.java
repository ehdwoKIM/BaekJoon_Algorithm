import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> myQueue = new LinkedList<>();
        int N = sc.nextInt();
        //큐에 N만큼 저장
        for (int i = 1; i <= N; i++) {
            myQueue.add(i);
        }
        while (myQueue.size() > 1) {
            myQueue.poll();
            myQueue.add(myQueue.poll());
        }
        //마지막 1장 남으면 출력
        System.out.println(myQueue.poll());
    }
}
