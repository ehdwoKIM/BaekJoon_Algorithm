import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] coords = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            coords[i][0] = Integer.parseInt(input[0]);
            coords[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(coords, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // x기준 오름차순
            return a[1] - b[1];                   // y기준 오름차순
        });

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n; i++) {
            output.append(coords[i][0]).append(" ").append(coords[i][1]).append("\n");
        }
        System.out.print(output);
    }
}
