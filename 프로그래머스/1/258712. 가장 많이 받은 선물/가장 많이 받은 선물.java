import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String,Integer> id = new HashMap<>();
        for (int i=0;i<n;i++) id.put(friends[i], i);

        int[][] cnt = new int[n][n];
        int[] sent = new int[n], recv = new int[n];

        for (String g: gifts) {
            String[] sp = g.split(" ");
            int a = id.get(sp[0]), b = id.get(sp[1]);
            cnt[a][b]++; sent[a]++; recv[b]++;
        }

        int[] nextRecv = new int[n];
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (cnt[i][j] > cnt[j][i]) nextRecv[i]++;
                else if (cnt[i][j] < cnt[j][i]) nextRecv[j]++;
                else {
                    int gi = sent[i] - recv[i];
                    int gj = sent[j] - recv[j];
                    if (gi > gj) nextRecv[i]++;
                    else if (gi < gj) nextRecv[j]++;
                }
            }
        }
        int ans = 0;
        for (int v: nextRecv) ans = Math.max(ans, v);
        return ans;
    }
}
