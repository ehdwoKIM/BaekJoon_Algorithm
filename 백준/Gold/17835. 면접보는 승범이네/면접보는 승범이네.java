import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static int n, m, k;
    static List<List<Link>> rev;   
    static int[] hubs;             
    static final long INF = Long.MAX_VALUE / 4;

    static class Link implements Comparable<Link> {
        int to;
        long cost;
        Link(int to, long cost) { this.to = to; this.cost = cost; }
        
        @Override 
        public int compareTo(Link o) { return Long.compare(this.cost, o.cost); }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        readInput();
        long[] dist = dijkstraFromHubs();  

        long far = -1;
        int city = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != INF && dist[i] > far) {
                far = dist[i];
                city = i;
            }
        }

        out.append(city).append('\n').append(far);
        System.out.print(out.toString());
    }


    static long[] dijkstraFromHubs() {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        boolean[] done = new boolean[n + 1];
        PriorityQueue<Link> pq = new PriorityQueue<>();

        for (int s : hubs) {
            if (dist[s] > 0) {      
                dist[s] = 0;
                pq.offer(new Link(s, 0));
            }
        }

        while (!pq.isEmpty()) {
            Link cur = pq.poll();
            if (done[cur.to]) continue;
            if (cur.cost != dist[cur.to]) continue;
            done[cur.to] = true;

            for (Link nx : rev.get(cur.to)) {
                long nd = cur.cost + nx.cost;
                if (nd < dist[nx.to]) {
                    dist[nx.to] = nd;
                    pq.offer(new Link(nx.to, nd));
                }
            }
        }
        return dist;
    }

    static void readInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        rev = new ArrayList<>();
        for (int i = 0; i <= n; i++) rev.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            rev.get(b).add(new Link(a, w));
        }

        hubs = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) hubs[i] = Integer.parseInt(st.nextToken());
    }
}
