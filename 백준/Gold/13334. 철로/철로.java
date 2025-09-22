import java.io.*;
import java.util.*;

public class Main {
    static class P{int s,e; P(int a,int b){s=Math.min(a,b); e=Math.max(a,b);} }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        List<P> seg=new ArrayList<>();
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            seg.add(new P(a,b));
        }
        int d=Integer.parseInt(br.readLine().trim());

        // 끝점 기준 정렬
        seg.sort(Comparator.comparingInt(o->o.e));

        PriorityQueue<Integer> minLeft = new PriorityQueue<>(); // 왼쪽 끝 최소 힙
        int ans=0;
        for(P cur: seg){
            minLeft.add(cur.s);
            int L = cur.e - d;
            while(!minLeft.isEmpty() && minLeft.peek() < L) minLeft.poll();
            ans = Math.max(ans, minLeft.size());
        }
        System.out.println(ans);
    }
}
