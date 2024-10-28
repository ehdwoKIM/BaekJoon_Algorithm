import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = 0, s = 0, m = 0;
		n = Integer.valueOf(st.nextToken());
		s = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		int[] v = new int[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=n; i++) {
			v[i] = Integer.valueOf(st.nextToken());
		}
		int[] dp = new int[m+1];
		for(int i=0; i<=m; i++) dp[i] = -1;
		
		solution(n, s, m, v, dp);
		
		for(int i=m; i>=0; i--) {
			if(dp[i] == n) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
	
	public static void solution(int n, int s, int m, int[] v, int[] dp) {
		dp[s] = 0;
		
		
		for(int v_idx=1; v_idx<=n; v_idx++) {
			Queue<Integer> q = new LinkedList<>();
			for(int dp_idx=0; dp_idx<=m; dp_idx++) {
				if(v_idx-1 == dp[dp_idx]) {
					int positive = dp_idx + v[v_idx];
					int negative = dp_idx - v[v_idx];
					
					if(positive <= m) q.add(positive);
					if(negative >= 0) q.add(negative);
				}
			}
			while(!q.isEmpty()) {
				int idx = q.poll();
				dp[idx] = v_idx;
			}
		}
	}
}