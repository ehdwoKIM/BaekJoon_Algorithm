import java.util.*;

public class Main {
    static int rockCount, goalHeight;
    static List<int[]> rockList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rockCount = scanner.nextInt();
        goalHeight = scanner.nextInt();

        rockList.add(new int[]{0, 0}); // 시작점 추가

        for (int i = 0; i < rockCount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            rockList.add(new int[]{x, y});
        }

        // x좌표 오름차순 정렬
        rockList.sort(Comparator.comparingInt(o -> o[0]));

        visited = new boolean[rockList.size()];
        System.out.println(findMinimumMoves());
    }

    static int findMinimumMoves() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0}); // 인덱스, 이동횟수
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentIndex = current[0];
            int moveCount = current[1];
            int[] currentRock = rockList.get(currentIndex);

            for (int nextIndex = currentIndex + 1; nextIndex < rockList.size(); nextIndex++) {
                int[] nextRock = rockList.get(nextIndex);

                if (nextRock[0] - currentRock[0] > 2) {
                    break; // x좌표 차이 2 넘으면 더 볼 필요 없음
                }

                if (!visited[nextIndex] &&
                    Math.abs(currentRock[1] - nextRock[1]) <= 2) {
                    if (nextRock[1] == goalHeight) {
                        return moveCount + 1;
                    }
                    visited[nextIndex] = true;
                    queue.add(new int[]{nextIndex, moveCount + 1});
                }
            }

            for (int prevIndex = currentIndex - 1; prevIndex >= 0; prevIndex--) {
                int[] prevRock = rockList.get(prevIndex);

                if (currentRock[0] - prevRock[0] > 2) {
                    break; // x좌표 차이 2 넘으면 끝
                }

                if (!visited[prevIndex] &&
                    Math.abs(currentRock[1] - prevRock[1]) <= 2) {
                    if (prevRock[1] == goalHeight) {
                        return moveCount + 1;
                    }
                    visited[prevIndex] = true;
                    queue.add(new int[]{prevIndex, moveCount + 1});
                }
            }
        }

        return -1; // 목표 못 가면 -1
    }
}
