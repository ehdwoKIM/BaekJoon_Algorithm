import java.util.*;

class Solution {
    static final int[] RATES = {10, 20, 30, 40};
    int bestSubs, bestRev;
    int U, E;

    public int[] solution(int[][] users, int[] emoticons) {
        bestSubs = -1; bestRev = -1;
        U = users.length; E = emoticons.length;
        int[] picked = new int[E];
        dfs(0, picked, users, emoticons);
        return new int[]{bestSubs, bestRev};
    }

    void dfs(int idx, int[] picked, int[][] users, int[] em) {
        if (idx == E) {
            int subs = 0, rev = 0;
            for (int ui = 0; ui < U; ui++) {
                int need = users[ui][0], limit = users[ui][1];
                int sum = 0;
                for (int i = 0; i < E; i++) {
                    if (picked[i] >= need) {
                        sum += em[i] * (100 - picked[i]) / 100;
                    }
                }
                if (sum >= limit) subs++;
                else rev += sum;
            }
            if (subs > bestSubs || (subs == bestSubs && rev > bestRev)) {
                bestSubs = subs; bestRev = rev;
            }
            return;
        }
        for (int r : RATES) {
            picked[idx] = r;
            dfs(idx + 1, picked, users, em);
        }
    }
}
