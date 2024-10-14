import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] graph = new int[10][10];
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[][] visited = new boolean[10][10];
    static int result = 987654321;

    public static void resetDir(int r, int c) {
        visited[r][c] = false;

        for (int i = 0; i < 4; i++) {
            int nR = r + dir[i][0];
            int nC = c + dir[i][1];
            visited[nR][nC] = false;
        }
    }

    public static int sumDir(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nR = r + dir[i][0];
            int nC = c + dir[i][1];
            if (visited[nR][nC]) {
                return -1;
            }
        }

        int sum = graph[r][c];
        for (int i = 0; i < 4; i++) {
            int nR = r + dir[i][0];
            int nC = c + dir[i][1];
            visited[nR][nC] = true;
            sum += graph[nR][nC];
        }
        return sum;
    }

    public static void dfs(int r, int c, int cnt, int cost) {
        if (cnt == 3) {
            result = Math.min(result, cost);
            return;
        }
        for (int i = r; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (visited[i][j]) continue;
                int sum = sumDir(i, j);
                if (sum != -1) {
                    dfs(i, j, cnt + 1, cost + sum);
                    resetDir(i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visited[i][j]) {
                    int sum = sumDir(i, j);
                    dfs(i, j, 1, sum);
                    resetDir(i, j);
                }
            }
        }
        System.out.println(result);
        scanner.close();
    }
}
