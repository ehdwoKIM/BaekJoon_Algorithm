import java.io.*;
import java.util.*;

public class Main {
    static class DSU {
        int[] p, sz;
        DSU(int n){ p=new int[n+1]; sz=new int[n+1]; for(int i=1;i<=n;i++){ p[i]=i; sz[i]=1; } }
        int f(int x){ return p[x]==x? x:(p[x]=f(p[x])); }
        boolean u(int a,int b){
            a=f(a); b=f(b); if(a==b) return false;
            if(sz[a] < sz[b]){ int t=a; a=b; b=t; }
            p[b]=a; sz[a]+=sz[b]; return true;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken()), K=Integer.parseInt(st.nextToken());
        int[][] edges=new int[M][2];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            edges[i][0]=Integer.parseInt(st.nextToken());
            edges[i][1]=Integer.parseInt(st.nextToken());
        }
        StringBuilder out=new StringBuilder();
        for(int s=0; s<K; s++){
            DSU d=new DSU(N);
            long sum=0; int used=0;
            for(int i=s;i<M;i++){
                if(d.u(edges[i][0], edges[i][1])){
                    sum += (long)(i+1);
                    used++;
                    if(used == N-1) break; // 조기 종료
                }
            }
            out.append(used == N-1 ? sum : 0).append(s==K-1? "" : " ");
        }
        System.out.println(out.toString());
    }
}
