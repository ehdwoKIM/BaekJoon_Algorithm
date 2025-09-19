import java.io.*;
import java.util.*;

public class Main {
    static class FastIn {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int  nextInt()  throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }

    static class Town {
        long x, w;
        Town(long x, long w){ this.x = x; this.w = w; }
    }

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn();
        int n = in.nextInt();

        Town[] a = new Town[n];
        long total = 0L;                         
        for (int i = 0; i < n; i++) {
            long x = in.nextLong();
            long w = in.nextLong();
            a[i] = new Town(x, w);
            total += w;
        }

        Arrays.sort(a, Comparator.comparingLong(t -> t.x)); 

        long acc = 0L;                                     
        for (int i = 0; i < n; i++) {
            acc += a[i].w;                                 
            if (acc * 2L >= total) {                        
                System.out.println(a[i].x);                 
                break;
            }
        }
    }
}
