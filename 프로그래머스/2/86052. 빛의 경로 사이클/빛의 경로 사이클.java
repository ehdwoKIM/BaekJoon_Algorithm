import java.util.*;

class Solution {
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public int[] solution(String[] grid) {
        int H = grid.length, W = grid[0].length();
        boolean[][][] vis = new boolean[H][W][4];
        ArrayList<Integer> cycles = new ArrayList<>();

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                for (int d = 0; d < 4; d++) {
                    if (vis[r][c][d]) continue;
                    int len = trace(grid, r, c, d, vis);
                    if (len > 0) cycles.add(len);
                }
            }
        }
        Collections.sort(cycles);
        int[] ans = new int[cycles.size()];
        for (int i = 0; i < cycles.size(); i++) ans[i] = cycles.get(i);
        return ans;
    }

    private int trace(String[] grid, int sr, int sc, int sd, boolean[][][] vis) {
        int H = grid.length, W = grid[0].length();
        int r = sr, c = sc, d = sd;
        int len = 0;

        while (!vis[r][c][d]) {
            vis[r][c][d] = true;
            len++;

            char ch = grid[r].charAt(c);
            if (ch == 'L') d = turnLeft(d);
            else if (ch == 'R') d = turnRight(d);

            r = (r + DR[d] + H) % H;
            c = (c + DC[d] + W) % W;
        }
        return len;
    }

    private int turnLeft(int d) {
        if (d == 0) return 2;
        if (d == 1) return 3;
        if (d == 2) return 1;
        return 0;
    }

    private int turnRight(int d) {
        if (d == 0) return 3;
        if (d == 1) return 2;
        if (d == 2) return 0;
        return 1;
    }
}
