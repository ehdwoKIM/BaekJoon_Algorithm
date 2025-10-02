import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        for (int[] e : roads) { g.get(e[0]).add(e[1]); g.get(e[1]).add(e[0]); }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(destination);
        dist[destination] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int nx : g.get(x)) {
                if (dist[nx] != -1) continue;
                dist[nx] = dist[x] + 1;
                q.add(nx);
            }
        }

        int[] ans = new int[sources.length];
        for (int i = 0; i < sources.length; i++) ans[i] = dist[sources[i]];
        return ans;
    }
}
