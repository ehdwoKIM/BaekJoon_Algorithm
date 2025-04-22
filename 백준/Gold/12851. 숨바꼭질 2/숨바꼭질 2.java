import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		
		int start = Integer.parseInt(tokenizer.nextToken());
		int target = Integer.parseInt(tokenizer.nextToken());
		
		int[] visitedTime = new int[100001];
		Arrays.fill(visitedTime, Integer.MAX_VALUE);
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { start, 0 });
		visitedTime[start] = 0;
		
		int minTime = 0;
		int pathCount = 0;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int position = current[0];
			int time = current[1];
			
			if (position == target) {
				minTime = time;
				pathCount = 1;
				break;
			}
			
			int[] nextPositions = { position - 1, position + 1, position * 2 };
			
			for (int next : nextPositions) {
				if (next >= 0 && next <= 100000 && visitedTime[next] >= time) {
					queue.offer(new int[] { next, time + 1 });
					visitedTime[next] = time;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int position = current[0];
			int time = current[1];
			
			if (time != minTime) break;
			if (position == target) pathCount++;
		}
		
		System.out.println(minTime);
		System.out.println(pathCount);
	}
}
