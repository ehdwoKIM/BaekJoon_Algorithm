import java.util.*;

class Solution {
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};

    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w){ this.a=a; this.b=b; this.w=w; }
    }

    static class DSU {
        int[] p, sz;
        DSU(int n){ p=new int[n]; sz=new int[n]; for(int i=0;i<n;i++){p[i]=i; sz[i]=1;} }
        int f(int x){ return p[x]==x? x : (p[x]=f(p[x])); }
        boolean u(int a,int b){
            a=f(a); b=f(b); if(a==b) return false;
            if(sz[a] < sz[b]){ int t=a; a=b; b=t; }
            p[b]=a; sz[a]+=sz[b]; return true;
        }
    }

    public int solution(int[][] land, int height) {
        int n = land.length;
        int[][] id = new int[n][n];
        for (int[] row : id) Arrays.fill(row, -1);
        int compCnt = 0;

        for (int x=0; x<n; x++){
            for (int y=0; y<n; y++){
                if (id[x][y] != -1) continue;
                bfsLabel(land, height, x, y, compCnt, id);
                compCnt++;
            }
        }

        HashMap<Long, Integer> minEdge = new HashMap<>();
        for (int x=0; x<n; x++){
            for (int y=0; y<n; y++){
                for (int d=0; d<4; d++){
                    int nx = x + DX[d], ny = y + DY[d];
                    if (nx<0 || ny<0 || nx>=n || ny>=n) continue;
                    int a = id[x][y], b = id[nx][ny];
                    if (a == b) continue;
                    int diff = Math.abs(land[x][y] - land[nx][ny]);
                    if (diff <= height) continue; 
                    long key = (((long)Math.min(a,b)) << 32) | (long)Math.max(a,b);
                    Integer cur = minEdge.get(key);
                    if (cur == null || diff < cur) minEdge.put(key, diff);
                }
            }
        }

        ArrayList<Edge> edges = new ArrayList<>(minEdge.size());
        for (Map.Entry<Long,Integer> e : minEdge.entrySet()){
            long k = e.getKey();
            int a = (int)(k >> 32);
            int b = (int)(k & 0xffffffffL);
            edges.add(new Edge(a, b, e.getValue()));
        }
        edges.sort(Comparator.comparingInt(o -> o.w));

        
        DSU dsu = new DSU(compCnt);
        long cost = 0;
        int used = 0;
        for (Edge e : edges){
            if (dsu.u(e.a, e.b)){
                cost += e.w;
                used++;
                if (used == compCnt - 1) break;
            }
        }
        return (int)cost;
    }

    void bfsLabel(int[][] land, int H, int sx, int sy, int label, int[][] id){
        int n = land.length;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        id[sx][sy] = label;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int d=0; d<4; d++){
                int nx = x + DX[d], ny = y + DY[d];
                if (nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if (id[nx][ny] != -1) continue;
                if (Math.abs(land[x][y] - land[nx][ny]) <= H){
                    id[nx][ny] = label;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}
