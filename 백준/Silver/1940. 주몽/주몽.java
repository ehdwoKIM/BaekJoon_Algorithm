import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //배열 정렬
        Arrays.sort(arr);
        int count = 0;
        int left = 0;
        int right = N-1;

        // 투 포인터 알고리즘
        while (left < right) {
            if (arr[left] + arr[right] == M) {
                count++;
                left++;
                right--;
            } else if (arr[left] + arr[right] < M) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(count);
        br.close();
    }
}