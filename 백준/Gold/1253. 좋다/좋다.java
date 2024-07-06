import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [] A = new long[N];
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 배열 입력
        for (int k = 0; k < N; k++) {
            A[k] = Long.parseLong(st.nextToken());
        }

        // 기존 배열 오름차순 정렬
        Arrays.sort(A);

        for (int k = 0; k < N; k++) {
            long target = A[k];
            int i = 0;
            int j = N-1;
            // 투포인터 사용
            while (i < j) {
                if (A[i]+A[j] > target) {
                    j--;
                } else if (A[i]+A[j] < target){
                    i++;
                } else {
                    //target = 서로 다른 두 수의 합
                    if (i!=k && j!=k){
                        count++;
                        break;
                    } else if (i == k) {
                        i++;
                    } else if (j == k) {
                        j--;
                    }
                }
            }
        }
        System.out.println(count);
        br.close();
    }

}
