import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        List<Integer> list = new ArrayList<>();
        
        int prev = -1;
        for(int num : arr) {
            if (num != prev) {
                list.add(num);
                prev = num;
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        

        return answer;
    }
}