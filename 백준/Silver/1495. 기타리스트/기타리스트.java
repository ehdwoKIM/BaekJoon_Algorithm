import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int S = Integer.parseInt(inputs[1]);
        int M = Integer.parseInt(inputs[2]);

        int[] diff = new int[N+1];
        inputs = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            diff[i] = Integer.parseInt(inputs[i-1]);
        }

        int[] volume = new int[M + 1];
        for(int i=0; i<=M; i++){ // initialize : 0은 S의 초기값이기 때문에 -1로 초기화 시킨 후 진행
            volume[i] = -1;
        }
        volume[S] = 0;

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=1; i<=N; i++){ // 연주 idx
            list.clear();
            for(int j=0; j<=M; j++) { // 볼륨 idx
                if(volume[j] == i-1) { // 이전 연주(i-1)가 j 볼륨으로 연주가 가능했으면
                    if (0 <= j - diff[i] && j - diff[i] <= M) { // 0~M 범위에 속한다면
                        list.add(j - diff[i]);
                    }
                    if (0 <= j + diff[i] && j + diff[i] <= M) {
                        list.add(j + diff[i]);
                    }
                }
            }
            for (int v : list) {
                volume[v] = i;
            }
        }

        for(int i=M; i>=0; i--){
            if(volume[i] == N){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
