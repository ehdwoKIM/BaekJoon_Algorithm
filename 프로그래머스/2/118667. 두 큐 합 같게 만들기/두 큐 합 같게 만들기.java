import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n1 = queue1.length, n2 = queue2.length;
        int L = n1 + n2;

        long s1 = 0, s2 = 0;
        for (int x : queue1) s1 += x;
        for (int x : queue2) s2 += x;
        long total = s1 + s2;
        if ((total & 1L) == 1L) return -1;   
        long target = total / 2;

        int[] arr = new int[L];
        System.arraycopy(queue1, 0, arr, 0, n1);
        System.arraycopy(queue2, 0, arr, n1, n2);

        long sum = s1;
        int l = 0, r = n1 - 1;
        int moves = 0;

        while (l < L && r < L) {
            if (sum == target) return moves;

            if (sum < target) {
                r++;
                if (r >= L) break;           
                sum += arr[r];
                moves++;
            } else {
                sum -= arr[l];
                l++;
                moves++;
            }
        }
        return -1;
    }
}
