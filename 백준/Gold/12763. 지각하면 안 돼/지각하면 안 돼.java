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
    static class Edge {
        int to, t, c;
        Edge(int to, int t, int c){ this.to = to; this.t = t; this.c = c; }
    }
    static class State {
        int v, t, m;
        State(int v, int t, int m){ this.v = v; this.t = t; this.m = m; }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();
        int N = in.nextInt();              
        int T = in.nextInt();              
        int M = in.nextInt();              
        int L = in.nextInt();              

        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i <= N; i++) g.add(new ArrayList<>());
        for (int i = 0; i < L; i++) {
            int a = in.nextInt(), b = in.nextInt(), tt = in.nextInt(), cc = in.nextInt();
            g.get(a).add(new Edge(b, tt, cc));
            g.get(b).add(new Edge(a, tt, cc));           
        }

        final int INF = 1_000_000_000;
        //v에 m원을 써서 도달할때 최소 시간
        int[][] bestTime = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) Arrays.fill(bestTime[i], INF);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.t));
        bestTime[1][0] = 0;
        pq.add(new State(1, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.t != bestTime[cur.v][cur.m]) continue; 
            if (cur.t > T) continue;                       

            for (Edge e : g.get(cur.v)) {
                int nm = cur.m + e.c;
                if (nm > M) continue;                     
                int nt = cur.t + e.t;
                if (nt < bestTime[e.to][nm]) {
                    bestTime[e.to][nm] = nt;
                    pq.add(new State(e.to, nt, nm));
                }
            }
        }

        //시간 ≤ T를 만족하는 최소지출
        int answer = -1;
        for (int mSpent = 0; mSpent <= M; mSpent++) {
            if (bestTime[N][mSpent] <= T) {
                answer = mSpent;
                break;
            }
        }
        System.out.println(answer);
    }
}
