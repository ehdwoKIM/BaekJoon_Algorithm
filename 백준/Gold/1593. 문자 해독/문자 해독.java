import java.io.*;
import java.util.*;

public class Main {
    static int id(char c){
        if('A'<=c && c<='Z') return c-'A';
        else return c-'a'+26; 
    }
    static boolean same(int[] a,int[] b){
        for(int i=0;i<52;i++) if(a[i]!=b[i]) return false;
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int g=Integer.parseInt(st.nextToken()), s=Integer.parseInt(st.nextToken());
        String W=br.readLine().trim();
        String S=br.readLine().trim();
        if(s<g){ System.out.println(0); return; }

        int[] need=new int[52], win=new int[52];
        for(int i=0;i<g;i++) need[id(W.charAt(i))]++;
        for(int i=0;i<g-1;i++) win[id(S.charAt(i))]++;

        int ans=0;
        for(int r=g-1; r<s; r++){
            win[id(S.charAt(r))]++;               
            if(same(need, win)) ans++;
            int l = r - g + 1;                     
            win[id(S.charAt(l))]--;                
        }
        System.out.println(ans);
    }
}
