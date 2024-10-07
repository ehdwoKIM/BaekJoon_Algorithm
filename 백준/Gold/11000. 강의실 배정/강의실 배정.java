import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][2];

        for(int i=0; i<N; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        //시작 시간 순서대로 정렬
        Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i=0; i<N; i++){
            if(!q.isEmpty() && q.peek() <= arr[i][0]) {
                q.poll();
            }
            q.add(arr[i][1]);
        }
        System.out.println(q.size());
    }
}
