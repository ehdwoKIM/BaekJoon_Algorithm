import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] grid;
    static int[][] comp;              
    static int[] sizeById;            
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) grid[i] = br.readLine().toCharArray();

        comp = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(comp[i], -1);

        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(0);
        int id = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (grid[x][y] == '0' && comp[x][y] == -1) {
                    int sz = bfsMark(x, y, id);
                    sizes.add(sz); //id+1 위치 sz 저장용
                    id++;
                }
            }
        }

        sizeById = new int[id];
        for (int k = 0; k < id; k++) {
            sizeById[k] = sizes.get(k + 1);
        }

        StringBuilder out = new StringBuilder();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (grid[x][y] == '1') {
                    int sum = 1;
                    //중복 방지
                    int a = -1, b = -1, c = -1, d = -1;
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + DX[dir], ny = y + DY[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        int nid = comp[nx][ny];
                        if (nid == -1) continue;
                        if (nid == a || nid == b || nid == c || nid == d) continue;
                        //빈 슬롯에만 넣기
                        if (a == -1) a = nid;
                        else if (b == -1) b = nid;
                        else if (c == -1) c = nid;
                        else if (d == -1) d = nid;
                    }
                    if (a != -1) sum += sizeById[a];
                    if (b != -1) sum += sizeById[b];
                    if (c != -1) sum += sizeById[c];
                    if (d != -1) sum += sizeById[d];

                    out.append(sum % 10);
                } else {
                    out.append('0');
                }
            }
            out.append('\n');
        }
        System.out.print(out.toString());
    }

    static int bfsMark(int sx, int sy, int id) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        comp[sx][sy] = id;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + DX[d], ny = y + DY[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (grid[nx][ny] != '0') continue;
                if (comp[nx][ny] != -1) continue;
                comp[nx][ny] = id;
                cnt++;
                q.add(new int[]{nx, ny});
            }
        }
        return cnt;
    }
}
