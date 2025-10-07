import java.io.*;
import java.util.*;

public class Main {
    static int[] parent, rank;
    static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean unite(int a, int b){
        a = find(a); b = find(b);
        if(a==b) return false;             
        if(rank[a] < rank[b]){ int t=a; a=b; b=t; }
        parent[b] = a;
        if(rank[a]==rank[b]) rank[a]++;
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()), m=Integer.parseInt(st.nextToken());
        parent = new int[n]; rank = new int[n];
        for(int i=0;i<n;i++){ parent[i]=i; rank[i]=0; }

        for(int i=1;i<=m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            if(!unite(a,b)){             
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}
