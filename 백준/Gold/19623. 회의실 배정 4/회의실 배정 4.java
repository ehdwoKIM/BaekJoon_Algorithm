import java.io.*;
import java.util.*;

public class Main {
    static class M{int s,e,w; M(int s,int e,int w){this.s=s;this.e=e;this.w=w;}}
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        M[] a=new M[n];
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            a[i]=new M(s,e,w);
        }
        Arrays.sort(a, (x,y)-> x.e==y.e? x.s-y.s : x.e-y.e);

        int[] ends=new int[n];
        for(int i=0;i<n;i++) ends[i]=a[i].e;

        long[] dp=new long[n+1]; 
        for(int i=1;i<=n;i++){
            int s = a[i-1].s;
            int p = upperBound(ends, s) - 1; // ends[p] <= s
            long take = dp[p+1] + a[i-1].w;
            long skip = dp[i-1];
            dp[i] = Math.max(skip, take);
        }
        System.out.println(dp[n]);
    }
    static int upperBound(int[] arr, int key){
        int l=0, r=arr.length;
        while(l<r){
            int m=(l+r)>>>1;
            if(arr[m]<=key) l=m+1; else r=m;
        }
        return l;
    }
}
