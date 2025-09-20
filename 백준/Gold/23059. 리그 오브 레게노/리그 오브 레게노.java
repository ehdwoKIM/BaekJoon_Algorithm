import java.io.*;
import java.util.*;

public class Main {

    static class FastIn {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();
        int N = in.nextInt();
        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        Set<String> all = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String A = in.next();
            String B = in.next();
            all.add(A); all.add(B);

            adj.computeIfAbsent(A, k -> new ArrayList<>()).add(B);
            indeg.put(B, indeg.getOrDefault(B, 0) + 1);
            // A도 indegree 맵에 존재하게
            indeg.putIfAbsent(A, indeg.getOrDefault(A, 0));
        }

        // 초기 라운드 후보: indegree=0
        List<String> cur = new ArrayList<>();
        for (String s : all) if (indeg.getOrDefault(s, 0) == 0) cur.add(s);
        Collections.sort(cur);

        StringBuilder out = new StringBuilder();
        int picked = 0;

        while (!cur.isEmpty()) {
            List<String> next = new ArrayList<>();

            //사전순으로 출력
            for (String name : cur) {
                out.append(name).append('\n');
                picked++;
            }

            //해당라운드 아이템들이 열어주는 다음 후보 수집
            for (String name : cur) {
                for (String nx : adj.getOrDefault(name, Collections.emptyList())) {
                    int d = indeg.get(nx) - 1;
                    indeg.put(nx, d);
                    if (d == 0) next.add(nx);
                }
            }

            Collections.sort(next);// 다음 라운드도 사전순
            cur = next;
        }

        if (picked != all.size()) {
            System.out.println(-1);
        } else {
            System.out.print(out.toString());
        }
    }
}
