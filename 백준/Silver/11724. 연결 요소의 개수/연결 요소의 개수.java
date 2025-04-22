import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] adjacencyList;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberOfNodes = scanner.nextInt();      // 정점개수
        int numberOfEdges = scanner.nextInt();      // 간선개수
        int connectedComponents = 0;

        visited = new boolean[numberOfNodes + 1];
        adjacencyList = new ArrayList[numberOfNodes + 1];

        for (int i = 1; i <= numberOfNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < numberOfEdges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adjacencyList[u].add(v);
            adjacencyList[v].add(u);  // 양방향 그래프
        }

        for (int node = 1; node <= numberOfNodes; node++) {
            if (!visited[node]) {
                dfs(node);
                connectedComponents++;
            }
        }

        System.out.println(connectedComponents);
    }

    private static void dfs(int currentNode) {
        visited[currentNode] = true;

        for (int neighbor : adjacencyList[currentNode]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
