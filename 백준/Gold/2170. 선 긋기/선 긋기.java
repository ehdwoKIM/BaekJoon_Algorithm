import java.io.*;
import java.util.*;

public class Main {
    static class Seg{long s,e;Seg(long s,long e){this.s=s;this.e=e;}}
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        Seg[] a=new Seg[N];
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            long x=Long.parseLong(st.nextToken());
            long y=Long.parseLong(st.nextToken());
            a[i]=new Seg(Math.min(x,y), Math.max(x,y));
        }
        Arrays.sort(a, (p,q)-> Long.compare(p.s, q.s));
        long ans=0, L=a[0].s, R=a[0].e;
        for(int i=1;i<N;i++){
            if(a[i].s<=R && a[i].e>=R){
                R=a[i].e; 
            }else if(a[i].s>R){
                ans+=R-L; L=a[i].s; R=a[i].e;
            }else{
            }
        }
        System.out.println(ans + (R-L));
    }
}
