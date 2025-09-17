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
    static class Seg {
        int s, e;
        Seg(int s, int e){ this.s = s; this.e = e; }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();
        int n = in.nextInt(), L = in.nextInt();

        Seg[] segs = new Seg[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt(), b = in.nextInt();
            if (a > b) { int t = a; a = b; b = t; }
            segs[i] = new Seg(a, b);
        }

        Arrays.sort(segs, (x, y) -> x.s - y.s);

        int cur = 0, used = 0;
        for (Seg g : segs) {
            if (cur < g.s) cur = g.s;
            if (cur >= g.e) continue;
            int need = g.e - cur;
            int cnt = (need + L - 1) / L;
            used += cnt;
            cur += cnt * L;
        }
        System.out.println(used);
    }
}
