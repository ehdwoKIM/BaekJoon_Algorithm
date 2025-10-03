import java.io.*;
import java.util.*;

public class Main {
    static class Edge { int u,v; int w; Edge(int u,int v,int w){ this.u=u; this.v=v; this.w=w; } }
    static int[] parent, size;

    static int find(int x){ return parent[x]==x? x : (parent[x]=find(parent[x])); }
    static boolean unite(int a,int b){
        a=find(a); b=find(b);
        if(a==b) return false;
        if(size[a] < size[b]){ int t=a; a=b; b=t; }
        parent[b]=a; size[a]+=size[b];
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges=new ArrayList<>(M);
        long total=0L;

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            edges.add(new Edge(a,b,w));
            total += (long)w;
        }

        Collections.sort(edges, Comparator.comparingInt(e->e.w));
        parent=new int[N+1]; size=new int[N+1];
        for(int i=1;i<=N;i++){ parent[i]=i; size[i]=1; }

        long mst=0L; int used=0;
        for(Edge e: edges){
            if(unite(e.u, e.v)){
                mst += (long)e.w;
                used++;
                if(used == N-1) break;//조기종료
            }
        }

        if(used != N-1) {
            System.out.println(-1);
        } else {
            System.out.println(total - mst);
        }
    }
}
