import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[]args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        //연산 횟수
        int n = scan.nextInt();

        for (int t = 0 ; t < n ; t++){
            int count=0;
            //자료 개수
            int m = scan.nextInt();
            //찾으려는 자료 
            int I = scan.nextInt();
            LinkedList<Integer[]> queue = new LinkedList<Integer[]>();
            int i = 0;
            while(i < m) {
                //인덱스, 우선순위
                Integer[] arr = {i , scan.nextInt()} ;
                //큐에 추가
                queue.add(arr);
                i++;

            }

            //맨 첫번째 꺼내기
            //나올수 있으면 꺼내고 (인덱스 일치 -> 다음 문제로)
            //나올 수 없으면 뒤로 보내기
            while( !queue.isEmpty()){
                Integer[] now = queue.poll();
                boolean pop = true;

                for(Integer[] q : queue) {
                    //우선순위가 더 높은 경우
                    if(q[1] > now[1])
                        pop = false;
                }
                
                //우선순위가 더 높은 원소가 없는 경우
                if(pop) {
                    count++;
                    if(now[0] == I) {
                        sb.append(count).append("\n");
                        break;
                    }
                }else
                    queue.add(now);
            }
        }
        System.out.print(sb);
    }
}
