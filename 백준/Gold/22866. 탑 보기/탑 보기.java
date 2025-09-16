import java.io.*;
import java.util.*;

public class Main {
    static class FastIn {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();

        int n = in.nextInt();                  // 건물 개수
        int[] h = new int[n];                  // 각 건물 높이
        for (int i = 0; i < n; i++) h[i] = in.nextInt();

        int[] leftCnt  = new int[n];
        int[] rightCnt = new int[n];
        int[] nearLeft = new int[n];
        int[] nearRight= new int[n];
        Arrays.fill(nearLeft,  -1);
        Arrays.fill(nearRight, -1);

        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && h[st.peek()] <= h[i]) st.pop();
            leftCnt[i] = st.size();

            if (!st.isEmpty()) nearLeft[i] = st.peek();

            st.push(i);
        }

        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && h[st.peek()] <= h[i]) st.pop();
            rightCnt[i] = st.size();
            if (!st.isEmpty()) nearRight[i] = st.peek();
            st.push(i);
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int total = leftCnt[i] + rightCnt[i];
            if (total == 0) {
                out.append(0).append('\n');
                continue;
            }
            int bestIdx = -1;
            int bestDist = Integer.MAX_VALUE;

            if (nearLeft[i] != -1) {
                int d = i - nearLeft[i];
                bestIdx = nearLeft[i];
                bestDist = d;
            }
            if (nearRight[i] != -1) {
                int d = nearRight[i] - i;
                if (d < bestDist) {
                    bestDist = d;
                    bestIdx = nearRight[i];
                } else if (d == bestDist && bestIdx != -1 && nearRight[i] < bestIdx) {
                    // 거리가 같으면 번호가 작은 쪽
                    bestIdx = nearRight[i];
                } else if (d == bestDist && bestIdx == -1) {
                    // 왼쪽 후보가 없었고 오른쪽 후보만 있는 경우
                    bestIdx = nearRight[i];
                }
            }

            out.append(total).append(' ').append(bestIdx + 1).append('\n');
        }

        System.out.print(out.toString());
    }
}
