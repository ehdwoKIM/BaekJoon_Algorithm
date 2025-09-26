import java.io.*;
import java.util.*;

public class Main {
    static class E{int t,d;E(int t,int d){this.t=t;this.d=d;}}
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        ArrayList<E> ev=new ArrayList<>(2*N);
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            ev.add(new E(s,+1));
            ev.add(new E(e,-1));
        }
        ev.sort(Comparator.comparingInt(o->o.t));
        int cur=0, best=0; long L=-1,R=-1;
        for(int i=0;i<ev.size()-1;i++){
            cur += ev.get(i).d; 
            int t=ev.get(i).t, nt=ev.get(i+1).t;
            if(cur>best){ best=cur; L=t; R=nt; } 
            else if(cur==best && L!=-1 && R==t) R=nt;
        }
        System.out.println(best+"\n"+L+" "+R);
    }
}
