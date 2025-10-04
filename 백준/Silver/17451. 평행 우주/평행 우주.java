import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());

        long need = 1;
        for (int i = n - 1; i >= 0; i--) {
            long x = a[i];
            if (need % x != 0) {
                need = ((need + x - 1) / x) * x;
            }
        }
        System.out.println(need);
    }
}
