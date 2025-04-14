import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] original = Arrays.stream(br.readLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        int[] sorted = Arrays.stream(original).distinct().toArray();
        Arrays.sort(sorted);

        // 랭크 매핑 (값 -> 순위)
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            rankMap.put(sorted[i], i);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int value : original) {
            sb.append(rankMap.get(value)).append(" ");
        }
        System.out.println(sb);
    }
}
