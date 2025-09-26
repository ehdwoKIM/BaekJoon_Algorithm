import java.io.*;
import java.util.*;

public class Main {
    static class Seg{long s,e;Seg(long s,long e){this.s=s;this.e=e;}}
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        Seg[] a=new Seg[N];
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            long x=Long.parseLong(st.nextToken());
            long y=Long.parseLong(st.nextToken());
            long s=Math.min(x,y), e=Math.max(x,y);
            a[i]=new Seg(s,e);
        }
        Arrays.sort(a, (p,q)-> {
            if(p.s==q.s) return Long.compare(p.e, q.e);
            return Long.compare(p.s, q.s);
        });

        long ans=0;
        long L=a[0].s, R=a[0].e;
        for(int i=1;i<N;i++){
            if(a[i].s <= R){
                if(a[i].e > R) R = a[i].e;
            }else{
                ans += (R - L);
                L = a[i].s; R = a[i].e;
            }
        }
        ans += (R - L);
        System.out.println(ans);
    }
}
