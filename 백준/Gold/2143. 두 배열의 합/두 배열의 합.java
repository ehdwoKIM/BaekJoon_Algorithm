import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine().trim());

        int n = Integer.parseInt(br.readLine().trim());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) A[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine().trim());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) B[i] = Integer.parseInt(st.nextToken());

        //모든 부분합
        ArrayList<Long> SA = new ArrayList<>();
        ArrayList<Long> SB = new ArrayList<>();
        for(int i=0;i<n;i++){
            long s=0;
            for(int j=i;j<n;j++){ s += A[j]; SA.add(s); }
        }
        for(int i=0;i<m;i++){
            long s=0;
            for(int j=i;j<m;j++){ s += B[j]; SB.add(s); }
        }

        Collections.sort(SA);
        Collections.sort(SB);

        //투포인터
        int p = 0, q = SB.size()-1;
        long ans = 0;
        while(p < SA.size() && q >= 0){
            long sum = SA.get(p) + SB.get(q);
            if(sum == T){
                long va = SA.get(p), vb = SB.get(q);
                long ca = 0, cb = 0;
                while(p < SA.size() && SA.get(p) == va){ ca++; p++; }
                while(q >= 0 && SB.get(q) == vb){ cb++; q--; }
                ans += ca * cb;
            }else if(sum < T) p++;
            else q--;
        }
        System.out.println(ans);
    }
}
