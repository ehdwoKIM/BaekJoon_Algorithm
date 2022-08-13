import java.util.Scanner;

public class Main {
	
	static int[] arr = new int[1001]; 
	static int[] N;
	static int result, a;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N1 = sc.nextInt();
		N = new int[N1+1];

		for(int i = 0; N1>i ; ++i) {
			N[i] = sc.nextInt();
		}
		for(int i = 0 ; N1 > i ; ++i) {
			System.out.println(dp(N[i]));
		}
	}
	
	public static int dp(int x) {
		
		if( x == 0 ) return 1; 
		if( x == 1 ) return 1;
		if( x == 2 ) return 5;
		if( x == 3 ) return 11;
		if(arr[x] != 0) return arr[x];

		result = dp(x-1) + 4*dp(x-2) ;
		
		for(int i=3; x>=i;++i) {
			if( i%2 == 0 ) {
				result += 3*dp(x-i);
			}
			if( i%2 != 0) {
				result += 2*dp(x-i);
			}
		}
		
		return arr[x] = result;
		
	}

} 