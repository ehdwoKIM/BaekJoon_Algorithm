import java.io.*;
import java.util.*;

public class Main {
    static class S { int s, e; S(int s, int e){ this.s = s; this.e = e; } }
    static class Using { int end, idx; Using(int end, int idx){ this.end=end; this.idx=idx; } }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        S[] a = new S[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = new S(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(a, (x,y) -> x.s==y.s ? x.e - y.e : x.s - y.s);

        PriorityQueue<Using> using = new PriorityQueue<>(Comparator.comparingInt(u -> u.end)); // (끝시각, 좌석)
        PriorityQueue<Integer> free = new PriorityQueue<>();//사용 가능한 좌석번호 최소힙
        int nextIdx = 1;               
        int[] cnt = new int[N + 2];

        for (S x : a) {
            //현재 시작 시각까지 끝난 좌석 모두 반납
            while (!using.isEmpty() && using.peek().end <= x.s) {
                free.add(using.poll().idx);
            }
            //가장 작은 좌석 번호 배정
            int idx = free.isEmpty() ? nextIdx++ : free.poll();
            cnt[idx]++;
            using.add(new Using(x.e, idx));
        }

        int seatCount = nextIdx - 1;
        StringBuilder sb = new StringBuilder();
        sb.append(seatCount).append('\n');
        for (int i = 1; i <= seatCount; i++) {
            sb.append(cnt[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
