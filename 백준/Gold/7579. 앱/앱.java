import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());

        int[] mem=new int[N], cost=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) mem[i]=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        int sumCost=0;
        for(int i=0;i<N;i++){ cost[i]=Integer.parseInt(st.nextToken()); sumCost+=cost[i]; }

        int[] dp=new int[sumCost+1];
        for(int i=0;i<N;i++){
            for(int c=sumCost; c>=cost[i]; c--){
                dp[c] = Math.max(dp[c], dp[c-cost[i]] + mem[i]);// 0/1 -> 역순
            }
        }
        int ans=0;
        for(int c=0;c<=sumCost;c++){
            if(dp[c] >= M){ ans=c; break; }
        }
        System.out.println(ans);
    }
}
