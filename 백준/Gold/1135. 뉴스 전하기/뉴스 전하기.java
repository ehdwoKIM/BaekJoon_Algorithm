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
        int n = in.nextInt();

        int[] boss = new int[n];
        for (int i = 0; i < n; i++) boss[i] = in.nextInt();

        List<List<Integer>> kids = new ArrayList<>(n);
        for (int i = 0; i < n; i++) kids.add(new ArrayList<>());

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (boss[i] == -1) root = i;
            else kids.get(boss[i]).add(i);
        }

        Deque<Integer> st = new ArrayDeque<>();
        List<Integer> order = new ArrayList<>(n);
        st.push(root);
        while (!st.isEmpty()) {
            int cur = st.pop();
            order.add(cur);
            for (int nxt : kids.get(cur)) st.push(nxt);
        }

        int[] mins = new int[n]; 
        for (int idx = order.size() - 1; idx >= 0; idx--) {
            
            int u = order.get(idx);
            List<Integer> ch = kids.get(u);
            if (ch.isEmpty()) {
                mins[u] = 0;
                continue;
            }
            int k = ch.size();
            int[] need = new int[k];
            for (int i = 0; i < k; i++) need[i] = mins[ch.get(i)];

            Arrays.sort(need);
            int done = 0;
            for (int i = 0; i < k; i++) {
                int t = need[k - 1 - i] + (i + 1);
                if (t > done) done = t;
            }
            mins[u] = done;
        }
        System.out.println(mins[root]);
    }
}
