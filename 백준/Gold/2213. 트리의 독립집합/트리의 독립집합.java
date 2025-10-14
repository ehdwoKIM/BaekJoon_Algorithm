import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] w;
    static ArrayList<Integer>[] g;
    static int[][] dp;          
    static boolean[] pick;      
    static int[] parent;

    static void dfs(int u, int p) {
        parent[u] = p;
        dp[u][0] = 0;
        dp[u][1] = w[u];
        for (int v : g[u]) {
            if (v == p) continue;
            dfs(v, u);
            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
            dp[u][1] += dp[v][0];
        }
    }

    static void trace(int u, int p, int chosenParent) {
        // 부모가 선택되었으면 u는 선택 불가
        if (chosenParent == 1) {
            pick[u] = false;
            for (int v : g[u]) if (v != p) trace(v, u, 0);
            return;
        }
        // 부모가 미선택이면 u를 선택/미선택 중 큰 쪽
        if (dp[u][1] > dp[u][0]) {
            pick[u] = true;
            for (int v : g[u]) if (v != p) trace(v, u, 1);
        } else {
            pick[u] = false;
            for (int v : g[u]) if (v != p) trace(v, u, 0);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        w = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) w[i] = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b); g[b].add(a);
        }

        dp = new int[N + 1][2];
        parent = new int[N + 1];
        dfs(1, 0);

        pick = new boolean[N + 1];
        if (dp[1][1] > dp[1][0]) trace(1, 0, 0); 
        else trace(1, 0, 0);

        int ans = Math.max(dp[1][0], dp[1][1]);
        StringBuilder order = new StringBuilder();
        ArrayList<Integer> sel = new ArrayList<>();
        for (int i = 1; i <= N; i++) if (pick[i]) sel.add(i);
        Collections.sort(sel);
        for (int x : sel) order.append(x).append(' ');

        System.out.println(ans);
        System.out.println(order.toString().trim());
    }
}
