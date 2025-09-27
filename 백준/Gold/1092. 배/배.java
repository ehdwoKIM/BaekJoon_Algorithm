import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        Integer[] crane=new Integer[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) crane[i]=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(br.readLine().trim());
        Integer[] box=new Integer[M];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) box[i]=Integer.parseInt(st.nextToken());

        Arrays.sort(crane, Collections.reverseOrder());
        Arrays.sort(box, Collections.reverseOrder());

        if(M>0 && box[0] > crane[0]){
            System.out.println(-1);
            return;
        }
        boolean[] used = new boolean[M];
        int[] pos = new int[N]; 
        int moved=0, time=0;

        while(moved < M){
            for(int i=0;i<N;i++){
                //크레인이 들 수 있는 다음 미사용 박스
                while(pos[i] < M){
                    if(!used[pos[i]] && crane[i] >= box[pos[i]]){
                        used[pos[i]] = true;
                        moved++;
                        pos[i]++; 
                        break;
                    }else{
                        pos[i]++;
                    }
                }
            }
            time++;
        }
        System.out.println(time);
    }
}
