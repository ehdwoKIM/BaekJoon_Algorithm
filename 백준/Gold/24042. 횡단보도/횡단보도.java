import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int v;     
        int idx;   
        Edge(int v, int idx){ this.v=v; this.idx=idx; }
    }
    static final long INF = Long.MAX_VALUE/4;

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        List<List<Edge>> g = new ArrayList<>();
        for(int i=0;i<=N;i++) g.add(new ArrayList<>());

        for(int i=1;i<=M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int idx = (i-1) % M;           
            g.get(a).add(new Edge(b, idx));
            g.get(b).add(new Edge(a, idx));
        }

        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(x->x[0]));
        dist[1]=0; pq.add(new long[]{0,1});

        while(!pq.isEmpty()){
            long[] cur=pq.poll();
            long t = cur[0]; int x=(int)cur[1];
            if (t != dist[x]) continue;

            for (Edge e: g.get(x)) {
                long mod = t % M;                 
                long wait = (e.idx - mod + M) % M;
                long nd = t + wait + 1;           
                if (nd < dist[e.v]) {
                    dist[e.v] = nd;
                    pq.add(new long[]{nd, e.v});
                }
            }
        }
        System.out.println(dist[N]);
    }
}
