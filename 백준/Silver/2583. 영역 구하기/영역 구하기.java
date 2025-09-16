import java.io.*;
import java.util.*;

public class Main {

    // 간단/빠른 입력 유틸
    static class FastIn {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    // 격자 4방향 (상, 하, 좌, 우) — 코테에서 가장 보편적
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();

        // 입력: M(세로), N(가로), K(직사각형 개수)
        int rows = in.nextInt();   // M
        int cols = in.nextInt();   // N
        int k = in.nextInt();      // K

        // 칠해진 칸(막힌 칸) 표시용 보드와 방문 배열
        boolean[][] blocked = new boolean[rows][cols];
        boolean[][] seen    = new boolean[rows][cols];

        // K개의 직사각형을 보드에 칠한다.
        // 문제는 좌표를 (x, y)로 주고, 반열림 [x1, x2), [y1, y2) 규칙.
        // 배열 인덱스는 [row=y][col=x]로 맵핑하면 실수 줄어든다.
        for (int i = 0; i < k; i++) {
            int x1 = in.nextInt();  // 좌하단 x
            int y1 = in.nextInt();  // 좌하단 y
            int x2 = in.nextInt();  // 우상단 x (미포함)
            int y2 = in.nextInt();  // 우상단 y (미포함)

            // 반열림으로 칠하기: y는 [y1, y2), x는 [x1, x2)
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    blocked[y][x] = true; // 이 칸은 막혔다(색칠됨)
                }
            }
        }

        // 결과(각 영역의 넓이)를 담을 리스트
        ArrayList<Integer> areas = new ArrayList<>();

        // 격자 전체를 훑으며, 빈칸인데 아직 방문 안 한 칸이면 BFS로 영역 하나 탐색
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (blocked[y][x] || seen[y][x]) continue; // 칠해졌거나 이미 처리한 칸은 스킵

                // 새 영역 시작: BFS 큐 생성, 시작점을 push하면서 바로 방문 처리
                Deque<int[]> q = new ArrayDeque<>();
                q.add(new int[]{y, x});
                seen[y][x] = true;

                int size = 0; // 이번 영역의 넓이 카운터

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cy = cur[0], cx = cur[1];
                    size++; // 큐에서 뺀 순간 이 칸 포함

                    // 4방향 이웃 체크
                    for (int dir = 0; dir < 4; dir++) {
                        int ny = cy + dy[dir];
                        int nx = cx + dx[dir];

                        // 경계 밖이면 패스
                        if (ny < 0 || ny >= rows || nx < 0 || nx >= cols) continue;

                        // 막혔거나 이미 방문한 칸이면 패스
                        if (blocked[ny][nx] || seen[ny][nx]) continue;

                        // 처음 보는 빈칸이면 큐에 넣고 방문 처리
                        seen[ny][nx] = true;
                        q.add(new int[]{ny, nx});
                    }
                }

                // BFS가 끝나면 연결된 빈칸 하나의 영역이 완성 → 넓이 추가
                areas.add(size);
            }
        }

        // 오름차순 정렬
        Collections.sort(areas);

        // 출력: 영역 개수
        System.out.println(areas.size());

        // 출력: 각 영역 넓이(오름차순)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < areas.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(areas.get(i));
        }
        System.out.println(sb.toString());
    }
}
