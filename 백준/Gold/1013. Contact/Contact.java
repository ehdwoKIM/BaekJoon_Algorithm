import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Pattern;

public class Main {
	public static void main (String[] args) throws java.lang.Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int test = Integer.parseInt(br.readLine());
	    for(int t=0;t<test;t++){
	        String str = br.readLine().trim();
	        String ptn="(100+1+|01)+";
	        System.out.println(str.matches(ptn)? "YES" : "NO");
	    }
	}
}