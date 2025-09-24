import java.io.*;
import java.util.*;

public class Main {
    static class M { int s,e; M(int s,int e){ this.s=s; this.e=e; } }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        M[] a=new M[n];
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            a[i]=new M(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(a, (x,y)-> x.s==y.s ? x.e-y.e : x.s-y.s);

        PriorityQueue<Integer> endMin = new PriorityQueue<>();// 진행 중 회의의 종료시각들
        int maxRooms = 0;

        for(M m: a){
            while(!endMin.isEmpty() && endMin.peek() <= m.s) endMin.poll();
            endMin.add(m.e);                 
            if(endMin.size() > maxRooms)// 동시 사용 방 최대값 갱신
                maxRooms = endMin.size();
        }
        System.out.println(maxRooms);
    }
}
