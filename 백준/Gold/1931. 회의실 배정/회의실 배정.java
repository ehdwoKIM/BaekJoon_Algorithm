import java.util.*;

public class Main {
    static class time{
        int start;
        int end;
        time(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<time> pq = new PriorityQueue<>(new Comparator<time>() {
            @Override
            public int compare(time o1, time o2) {
                if(o1.end == o2.end){
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });
        for(int i=0;i<n;i++){
            pq.add(new time(sc.nextInt(), sc.nextInt()));
        }
        int ans = 0;
        int last_time = 0;
        for(int i=0;i<n;i++){
            time t = pq.poll();
            if(last_time<=t.start){
                ans++;
                last_time = t.end;
            }
        }
        System.out.println(ans);
    }
}