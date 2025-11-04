import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        // dp는 [행][열] = [y][x] 로 둘게요. 문제 정의에서 m=열(가로), n=행(세로)
        int[][] dp = new int[n + 1][m + 1];
        boolean[][] blocked = new boolean[n + 1][m + 1];

        // 웅덩이 마킹: puddles의 원소는 [x, y] (1-based, x=열, y=행)
        for (int[] p : puddles) {
            int x = p[0];
            int y = p[1];
            if (1 <= x && x <= m && 1 <= y && y <= n) {
                blocked[y][x] = true;
            }
        }

        // 시작점이 웅덩이면 경로 0
        if (blocked[1][1]) return 0;
        dp[1][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (y == 1 && x == 1) continue; // 시작점
                if (blocked[y][x]) {
                    dp[y][x] = 0;
                } else {
                    long fromUp = dp[y - 1][x];
                    long fromLeft = dp[y][x - 1];
                    dp[y][x] = (int)((fromUp + fromLeft) % MOD);
                }
            }
        }
        return dp[n][m];
    }
}
