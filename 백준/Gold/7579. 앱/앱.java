import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        int[] mem=new int[N], cost=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) mem[i]=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) cost[i]=Integer.parseInt(st.nextToken());

        int maxMem=0; for(int x:mem) maxMem+=x; 
        int[] dp=new int[maxMem+1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0]=0;
        for(int i=0;i<N;i++){
            for(int m=maxMem; m>=mem[i]; m--){
                dp[m]=Math.min(dp[m], dp[m-mem[i]]+cost[i]);
            }
        }
        int ans=1_000_000_000;
        for(int m=M; m<=maxMem; m++) ans=Math.min(ans, dp[m]);
        System.out.println(ans);
    }
}
