import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long dist = 0;
        int i = n - 1, j = n - 1;   

        while (i >= 0 || j >= 0) {
            while (i >= 0 && deliveries[i] == 0) i--;
            while (j >= 0 && pickups[j] == 0) j--;
            if (i < 0 && j < 0) break;

            int far = Math.max(i, j);       
            dist += (long)(far + 1) * 2;    

            // 이번 라운드에서 트럭용량만큼 배달/수거
            int left = cap, right = cap;

            // 배달
            while (i >= 0 && left > 0) {
                if (deliveries[i] <= left) {
                    left -= deliveries[i];
                    deliveries[i] = 0;
                    i--;
                } else {
                    deliveries[i] -= left;
                    left = 0;
                }
            }

            // 수거
            while (j >= 0 && right > 0) {
                if (pickups[j] <= right) {
                    right -= pickups[j];
                    pickups[j] = 0;
                    j--;
                } else {
                    pickups[j] -= right;
                    right = 0;
                }
            }
        }
        return dist;
    }
}
