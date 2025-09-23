import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        int[][] a=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) a[i][j]=Integer.parseInt(st.nextToken());
            Arrays.sort(a[i]);
        }
        int[] idx=new int[N];
        int best=Integer.MAX_VALUE;
        while(true){
            int mn=Integer.MAX_VALUE, mx=Integer.MIN_VALUE, k=-1;
            for(int i=0;i<N;i++){
                mn=Math.min(mn, a[i][idx[i]]);
                mx=Math.max(mx, a[i][idx[i]]);
            }
            best=Math.min(best, mx-mn);
            for(int i=0;i<N;i++){
                if(a[i][idx[i]]==mn){ k=i; break; }
            }
            idx[k]++;
            if(idx[k]==M) break; 
        }
        System.out.println(best);
    }
}
