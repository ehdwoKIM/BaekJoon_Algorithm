import java.io.*;
import java.util.*;

public class Main {
    static class FastIn {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    static class Task {
        int d, w;
        Task(int d, int w){ this.d = d; this.w = w; }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();
        int n = in.nextInt();

        Task[] a = new Task[n];
        int maxD = 0;
        for (int i = 0; i < n; i++) {
            int d = in.nextInt(), w = in.nextInt();
            a[i] = new Task(d, w);
            if (d > maxD) maxD = d;
        }

        //마감 오름차순
        Arrays.sort(a, Comparator.comparingInt(t -> t.d));
        //지금까지 선택한 과제들의 점수들 저장
        PriorityQueue<Integer> chosen = new PriorityQueue<>();

        int idx = 0;
        long sum = 0;

        // 힙 크기가 day를 넘으면 가장 작은 점수를 버린다
        for (int day = 1; day <= maxD; day++) {
            while (idx < n && a[idx].d == day) {
                chosen.add(a[idx].w);
                sum += a[idx].w;
                idx++;
            }
            // day일까지 최대 day개만
            while (chosen.size() > day) {
                sum -= chosen.poll();
            }
        }

        System.out.println(sum);
    }
}
