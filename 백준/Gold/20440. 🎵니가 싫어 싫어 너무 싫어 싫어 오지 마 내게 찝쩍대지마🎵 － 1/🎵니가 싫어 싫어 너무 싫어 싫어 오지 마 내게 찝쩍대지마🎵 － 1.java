import java.io.*;
import java.util.*;

public class Main {
    static class E{int t, d; E(int t,int d){this.t=t; this.d=d;}}
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        ArrayList<E> ev=new ArrayList<>(2*N);
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            ev.add(new E(s, +1)); 
            ev.add(new E(e, -1)); 
        }
        ev.sort(Comparator.comparingInt(o->o.t));

        int cur=0, best=0;
        long bestL=-1, bestR=-1;
        boolean taken=false; 
        for(int i=0;i<ev.size();){
            int t = ev.get(i).t;
            int sum=0;
            // 같은시각 델타 합치기
            while(i<ev.size() && ev.get(i).t==t){
                sum += ev.get(i).d;
                i++;
            }
            cur += sum; 
            // 다음 이벤트 시각
            int nextT = (i<ev.size() ? ev.get(i).t : t);
            if(i<ev.size()){ 
                if(cur > best){
                    best = cur;
                    bestL = t;
                    bestR = nextT;
                    taken = true;
                } else if(cur == best && taken && bestR == t){
                    bestR = nextT;
                }
            }
        }
        System.out.println(best);
        System.out.println(bestL + " " + bestR);
    }
}
