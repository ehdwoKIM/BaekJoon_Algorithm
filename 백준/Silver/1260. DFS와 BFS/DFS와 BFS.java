import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodeCount = scanner.nextInt();     // 정점 개수
        int edgeCount = scanner.nextInt();     // 간선 개수
        int startNode = scanner.nextInt();     // 시작 정점

        int[][] adjacencyMatrix = new int[nodeCount + 1][nodeCount + 1];
        boolean[] visited = new boolean[nodeCount + 1];

        // 인접 행렬 입력
        for (int i = 0; i < edgeCount; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            adjacencyMatrix[from][to] = 1;
            adjacencyMatrix[to][from] = 1;
        }

        // DFS 실행
        runDFS(adjacencyMatrix, visited, startNode);
        System.out.println();

        // BFS 실행
        Arrays.fill(visited, false);
        runBFS(adjacencyMatrix, visited, startNode);
    }

    private static void runDFS(int[][] graph, boolean[] visited, int current) {
        visited[current] = true;
        System.out.print(current + " ");

        for (int next = 1; next < graph.length; next++) {
            if (graph[current][next] == 1 && !visited[next]) {
                runDFS(graph, visited, next);
            }
        }
    }

    private static void runBFS(int[][] graph, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int next = 1; next < graph.length; next++) {
                if (graph[current][next] == 1 && !visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}
