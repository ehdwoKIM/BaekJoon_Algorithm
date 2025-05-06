import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer config = new StringTokenizer(inputReader.readLine());
        int totalDays = Integer.parseInt(config.nextToken());
        int analysisSpan = Integer.parseInt(config.nextToken());

        int[] visitorLogs = new int[totalDays];
        StringTokenizer logs = new StringTokenizer(inputReader.readLine());
        for (int i = 0; i < totalDays; i++) {
            visitorLogs[i] = Integer.parseInt(logs.nextToken());
        }

        int currentSum = 0;
        for (int i = 0; i < analysisSpan; i++) {
            currentSum += visitorLogs[i];
        }

        int maxVisitors = currentSum;
        int sameMaxCount = 1;

        for (int day = analysisSpan; day < totalDays; day++) {
            currentSum = currentSum - visitorLogs[day - analysisSpan] + visitorLogs[day];

            if (currentSum > maxVisitors) {
                maxVisitors = currentSum;
                sameMaxCount = 1;
            } else if (currentSum == maxVisitors) {
                sameMaxCount++;
            }
        }

        if (maxVisitors == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxVisitors);
            System.out.println(sameMaxCount);
        }
    }
}
