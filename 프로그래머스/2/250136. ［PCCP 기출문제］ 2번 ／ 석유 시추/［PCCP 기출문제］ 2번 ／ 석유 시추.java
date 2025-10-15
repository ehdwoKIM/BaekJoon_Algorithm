import java.util.*;

class Solution {
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};

    public int solution(int[][] land) {
        int n = land.length, m = land[0].length;

        //연결요소 라벨링
        int[][] comp = new int[n][m];
        for (int[] row : comp) Arrays.fill(row, -1);
        ArrayList<Integer> sizes = new ArrayList<>();
        int cid = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && comp[i][j] == -1) {
                    sizes.add(bfsLabel(i, j, cid, land, comp));
                    cid++;
                }
            }
        }

        //해당 열에 닿는 컴포넌트 집합을 모아 크기 합산
        int answer = 0;
        for (int c = 0; c < m; c++) {
            //cid 개수가 많을 수도 있으니 HashSet 사용
            HashSet<Integer> seen = new HashSet<>();
            int sum = 0;
            for (int r = 0; r < n; r++) {
                int id = comp[r][c];
                if (id != -1 && seen.add(id)) {
                    sum += sizes.get(id);
                }
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    private int bfsLabel(int sx, int sy, int cid, int[][] land, int[][] comp) {
        int n = land.length, m = land[0].length;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        comp[sx][sy] = cid;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + DX[k], ny = y + DY[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (land[nx][ny] == 1 && comp[nx][ny] == -1) {
                    comp[nx][ny] = cid;
                    cnt++;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return cnt;
    }
}
