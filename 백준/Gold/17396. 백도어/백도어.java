import java.io.*;
import java.util.*;

public class Main {
    static class E{int v; int w; E(int v,int w){this.v=v; this.w=w;}}
    static final long INF = Long.MAX_VALUE/4;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        int[] watch=new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) watch[i]=Integer.parseInt(st.nextToken());

        List<List<E>> g=new ArrayList<>();
        for(int i=0;i<N;i++) g.add(new ArrayList<>());
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken()), b=Integer.parseInt(st.nextToken()), w=Integer.parseInt(st.nextToken());
            g.get(a).add(new E(b,w)); g.get(b).add(new E(a,w));
        }

        long[] dist=new long[N]; Arrays.fill(dist, INF);
        PriorityQueue<long[]> pq=new PriorityQueue<>(Comparator.comparingLong(x->x[0]));
        if(watch[0]==1){ System.out.println(-1); return; }      
        dist[0]=0; pq.add(new long[]{0, 0});

        while(!pq.isEmpty()){
            long[] cur=pq.poll();
            long d = cur[0]; int x = (int)cur[1];
            if(d!=dist[x]) continue;
            if(x==N-1){ System.out.println(d); return; }        

            for(E e: g.get(x)){
                if(watch[e.v]==1 && e.v!=N-1) continue;         
                long nd = d + (long)e.w;
                if(nd < dist[e.v]){
                    dist[e.v]=nd;
                    pq.add(new long[]{nd, e.v});
                }
            }
        }
        System.out.println(-1);
    }
}
