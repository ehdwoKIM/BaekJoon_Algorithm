import java.io.*;
import java.util.*;

public class Main {
    static class E{int to,w; E(int t,int w){to=t;this.w=w;}}
    static class Node{int v,cap; Node(int v,int c){this.v=v;cap=c;}}
    public static void main(String[] a)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        List<List<E>> g=new ArrayList<>(); for(int i=0;i<=N;i++) g.add(new ArrayList<>());
        int maxW=0;
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken()), v=Integer.parseInt(st.nextToken()), w=Integer.parseInt(st.nextToken());
            g.get(u).add(new E(v,w)); g.get(v).add(new E(u,w));
            maxW=Math.max(maxW,w);
        }
        st=new StringTokenizer(br.readLine());
        int S=Integer.parseInt(st.nextToken()), T=Integer.parseInt(st.nextToken());

        int[] best=new int[N+1]; Arrays.fill(best, -1);
        PriorityQueue<Node> pq=new PriorityQueue<>((x,y)->y.cap-x.cap);
        best[S]=Integer.MAX_VALUE; pq.add(new Node(S, Integer.MAX_VALUE));
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            if(cur.cap<best[cur.v]) continue;
            for(E e:g.get(cur.v)){
                int nc=Math.min(cur.cap, e.w);
                if(nc>best[e.to]){
                    best[e.to]=nc;
                    pq.add(new Node(e.to, nc));
                }
            }
        }
        System.out.println(best[T]); 
    }
}
