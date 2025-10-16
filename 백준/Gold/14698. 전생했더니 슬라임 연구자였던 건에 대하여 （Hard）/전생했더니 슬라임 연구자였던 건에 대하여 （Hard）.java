import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
    static final BigInteger MOD = BigInteger.valueOf(1_000_000_007L);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int n = Integer.parseInt(nextToken(br));
            PriorityQueue<BigInteger> pq = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                pq.add(new BigInteger(nextToken(br)));
            }

            BigInteger ans = BigInteger.ONE;
            if (n == 1) {
                out.append(ans.mod(MOD)).append('\n');
                continue;
            }

            while (pq.size() > 1) {
                BigInteger a = pq.poll();
                BigInteger b = pq.poll();
                BigInteger c = a.multiply(b);          
                ans = ans.multiply(c).mod(MOD);        
                pq.add(c);
            }
            out.append(ans.mod(MOD)).append('\n');
        }
        System.out.print(out.toString());
    }

    private static String nextToken(BufferedReader br) throws IOException {
        int ch;
        StringBuilder sb = new StringBuilder();
        while (true) {
            br.mark(1);
            ch = br.read();
            if (ch == -1) return sb.length() == 0 ? null : sb.toString();
            if (!Character.isWhitespace(ch)) { sb.append((char) ch); break; }
        }
        while (true) {
            br.mark(1);
            ch = br.read();
            if (ch == -1 || Character.isWhitespace(ch)) break;
            sb.append((char) ch);
        }
        return sb.toString();
    }
}
