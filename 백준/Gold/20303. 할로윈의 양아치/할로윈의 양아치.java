import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), K = sc.nextInt();

        int[] candy = new int[n+1];
        for (int i = 1; i <= n; i++) candy[i] = sc.nextInt();

        List<List<Integer>> g = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            g.get(a).add(b); g.get(b).add(a);
        }

        boolean[] seen = new boolean[n+1];
        List<int[]> groups = new ArrayList<>(); // {size, sumCandy}
        for (int i = 1; i <= n; i++) {
            if (seen[i]) continue;
            int cnt = 0, sum = 0;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(i); seen[i] = true;
            while (!q.isEmpty()) {
                int cur = q.poll();
                cnt++; sum += candy[cur];
                for (int nx : g.get(cur)) if (!seen[nx]) { seen[nx] = true; q.add(nx); }
            }
            groups.add(new int[]{cnt, sum});
        }

        int G = groups.size();
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
