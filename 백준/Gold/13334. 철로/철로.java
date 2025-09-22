import java.io.*;
import java.util.*;

public class Main {
    static class Seg {
        int s, e;
        Seg(int a, int b){ s = Math.min(a,b); e = Math.max(a,b); }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        List<Seg> raw=new ArrayList<>();
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            raw.add(new Seg(a,b));
        }
        int d=Integer.parseInt(br.readLine().trim());

        //길이 d 초과 구간 제거
        List<Seg> seg=new ArrayList<>();
        for(Seg p: raw) if (p.e - p.s <= d) seg.add(p);

        //오른쪽 끝 오름차순
        seg.sort(Comparator.comparingInt(o->o.e));

        //현재 왼쪽경계보다 작은 구간들을 pop
        PriorityQueue<Integer> minLeft = new PriorityQueue<>();
        int ans=0;
        for(Seg cur: seg){
            minLeft.add(cur.s);
            int L = cur.e - d;                 
            while(!minLeft.isEmpty() && minLeft.peek() < L) minLeft.poll();
            ans = Math.max(ans, minLeft.size());
        }
        System.out.println(ans);
    }
}
