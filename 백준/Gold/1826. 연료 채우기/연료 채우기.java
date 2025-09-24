import java.io.*;
import java.util.*;

public class Main {
    static class Station { int x,f; Station(int x,int f){ this.x=x; this.f=f; } }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        Station[] st=new Station[N+1];
        for(int i=0;i<N;i++){
            StringTokenizer t=new StringTokenizer(br.readLine());
            st[i]=new Station(Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()));
        }
        StringTokenizer tt=new StringTokenizer(br.readLine());
        int L=Integer.parseInt(tt.nextToken()), P=Integer.parseInt(tt.nextToken());

        //목적지를 연료 0짜리 가짜 주유소로 추가
        st[N]=new Station(L, 0);
        Arrays.sort(st, Comparator.comparingInt(z->z.x));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int reach = P;//도달 가능한 최장거리
        int idx = 0, stops = 0;
        while(reach < L){
            while(idx < st.length && st[idx].x <= reach){
                pq.add(st[idx].f);
                idx++;
            }
            if(pq.isEmpty()){
                System.out.println(-1);
                return;
            }
            reach += pq.poll();
            stops++;
        }
        System.out.println(stops);
    }
}
