import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, cap, rev;
        Edge(int to,int cap,int rev){ this.to=to; this.cap=cap; this.rev=rev; }
    }
    static final int SRC = 0;     // 'A'
    static final int SNK = 25;    // 'Z'
    static final int V = 52;

    static ArrayList<Edge>[] g = new ArrayList[V];
    static int[] level = new int[V];
    static int[] work = new int[V];

    static int map(char c){
        if ('A' <= c && c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }

    static void addEdge(int u, int v, int c){
        Edge a = new Edge(v, c, g[v].size());
        Edge b = new Edge(u, 0, g[u].size());
        g[u].add(a); g[v].add(b);
    }

    static boolean bfs(){
        Arrays.fill(level, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        level[SRC] = 0;
        q.add(SRC);
        while(!q.isEmpty()){
            int x = q.poll();
            for (Edge e: g[x]){
                if (e.cap > 0 && level[e.to] == -1){
                    level[e.to] = level[x] + 1;
                    q.add(e.to);
                }
            }
        }
        return level[SNK] != -1;
    }

    static int dfs(int x, int f){
        if (x == SNK) return f;
        for (int i = work[x]; i < g[x].size(); i++, work[x] = i){
            Edge e = g[x].get(i);
            if (e.cap <= 0 || level[e.to] != level[x] + 1) continue;
            int ret = dfs(e.to, Math.min(f, e.cap));
            if (ret > 0){
                e.cap -= ret;
                g[e.to].get(e.rev).cap += ret;
                return ret;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        for (int i=0;i<V;i++) g[i]=new ArrayList<>();

        int M = Integer.parseInt(br.readLine().trim());
        for (int i=0;i<M;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int u = map(st.nextToken().charAt(0));
            int v = map(st.nextToken().charAt(0));
            int w = Integer.parseInt(st.nextToken());

            addEdge(u, v, w);
            addEdge(v, u, w);
        }

        long flow = 0;
        while (bfs()){
            Arrays.fill(work, 0);
            int pushed;
            while ((pushed = dfs(SRC, Integer.MAX_VALUE)) > 0){
                flow += pushed;
            }
        }
        System.out.println(flow);
    }
}
