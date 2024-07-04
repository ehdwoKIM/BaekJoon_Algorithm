import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] S = new long[N];
        long[] C = new long[M];
        long result = 0;
        S[0] = sc.nextInt();
        // 합배열
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + sc.nextInt();
        }
        // 합배열의 모든 값에 % M
        for (int i = 0; i < N; i++) {
            int remainder = (int)(S[i] % M);
            // 나머지가 0일 때 result에 저장
            if (remainder == 0) {
                result ++;
            }
            // 나머지가 같은 인덱스 개수 카운트
            C[remainder]++;
        }
        for (int i = 0; i < M; i++) {
            if (C[i] > 1) {
                // 나머지가 같은 인덱스 중 2개 뽑기
                result = result + (C[i] * (C[i]-1) / 2);
            }
        }
        System.out.println(result);
    }
}
