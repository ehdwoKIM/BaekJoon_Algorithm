import java.util.*;

class Solution {
    static class Edge {
        int v, w;
        Edge(int v, int w){ this.v=v; this.w=w; }
    }
    static class Node {
        int x, d;
        Node(int x, int d){ this.x=x; this.d=d; }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());

        for (int[] p : paths) {
            int a = p[0], b = p[1], w = p[2];
            g.get(a).add(new Edge(b, w));
            g.get(b).add(new Edge(a, w));
        }

        boolean[] isGate = new boolean[n + 1];
        for (int x : gates) isGate[x] = true;

        boolean[] isSummit = new boolean[n + 1];
        for (int x : summits) isSummit[x] = true;

        int INF = 1_000_000_000;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.d));
        for (int s : gates) {
            dist[s] = 0;
            pq.add(new Node(s, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d != dist[cur.x]) continue;
            if (isSummit[cur.x]) continue; 

            for (Edge e : g.get(cur.x)) {
                int nd = Math.max(cur.d, e.w); 
                if (dist[e.v] > nd && !isGate[e.v]) { 
                    dist[e.v] = nd;
                    pq.add(new Node(e.v, nd));
                }
            }
        }

        Arrays.sort(summits);
        int bestSummit = -1, bestIntensity = INF;
        for (int s : summits) {
            if (dist[s] < bestIntensity) {
                bestIntensity = dist[s];
                bestSummit = s;
            }
        }
        return new int[]{bestSummit, bestIntensity};
    }
}
