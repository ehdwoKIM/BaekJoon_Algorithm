import java.io.*;
import java.util.*;

public class Main {
    static class In {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        In in = new In();
        int n = in.nextInt(), m = in.nextInt(), K = in.nextInt();

        int[] candy = new int[n+1];
        for (int i = 1; i <= n; i++) candy[i] = in.nextInt();

        List<List<Integer>> g = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            g.get(a).add(b); g.get(b).add(a);
        }

        boolean[] seen = new boolean[n+1];
        List<int[]> groups = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (seen[i]) continue;
            int cnt = 0, sum = 0;
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            dq.add(i); seen[i] = true;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                cnt++; sum += candy[cur];
                for (int nx : g.get(cur)) if (!seen[nx]) { seen[nx] = true; dq.add(nx); }
            }
            groups.add(new int[]{cnt, sum});
        }

        int G = groups.size();
        //2차원 DP
        int[][] dp = new int[G+1][K];
        for (int i = 1; i <= G; i++) {
            int sz = groups.get(i-1)[0];
            int val = groups.get(i-1)[1];
            for (int w = 0; w < K; w++) {
                dp[i][w] = dp[i-1][w];
                if (w - sz >= 0) dp[i][w] = Math.max(dp[i][w], dp[i-1][w - sz] + val);
            }
        }
        int ans = 0;
        for (int w = 0; w < K; w++) ans = Math.max(ans, dp[G][w]);
        System.out.println(ans);
    }
}
