import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayDeque<int[]> q = new ArrayDeque<>(); 
        int[] cnt = new int[10];
        for (int i = 0; i < priorities.length; i++) {
            q.add(new int[]{priorities[i], i});
            cnt[priorities[i]]++;
        }
        int curMax = 9;
        while (curMax > 0 && cnt[curMax] == 0) curMax--;

        int printed = 0;
        while (!q.isEmpty()) {
            int[] now = q.pollFirst();
            int p = now[0], idx = now[1];

            if (p < curMax) {
                q.addLast(now);
                continue;
            }

            printed++;
            cnt[p]--;
            if (idx == location) return printed;

            while (curMax > 0 && cnt[curMax] == 0) curMax--;
        }
        return printed;
    }
}
