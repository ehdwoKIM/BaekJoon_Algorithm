import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer setup = new StringTokenizer(reader.readLine());
        int totalStudents = Integer.parseInt(setup.nextToken());
        int maxDistance = Integer.parseInt(setup.nextToken());

        Queue<Integer>[] recentCounts = new Queue[21]; //길이별 큐
        for (int i = 1; i <= 20; i++) {
            recentCounts[i] = new LinkedList<>();
        }

        long friendlyPairs = 0;
        for (int order = 0; order < totalStudents; order++) {
            String name = reader.readLine();
            int nameLength = name.length();

            // 큐에서 범위 넘어간 친구 제거
            while (!recentCounts[nameLength].isEmpty() && order - recentCounts[nameLength].peek() > maxDistance) {
                recentCounts[nameLength].poll();}

            // 현재큐에 있는 친구 수=좋은친구 수
            friendlyPairs += recentCounts[nameLength].size();
            // 현재학생 인덱스 저장
            recentCounts[nameLength].add(order);
        }
        System.out.println(friendlyPairs);
    }
}
