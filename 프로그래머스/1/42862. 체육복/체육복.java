import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int answer = 0;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        List<Integer> newLost = new ArrayList<>();
        List<Integer> newReserve = new ArrayList<>();

        for(int a : lost) {
            newLost.add(a);
        }
        
        int cnt = 0; //겹치는학생수
        for(int b : reserve) {
            if (!newLost.contains(b)) {
                newReserve.add(b);
            } else {
                cnt++;
                newLost.remove(Integer.valueOf(b));
            }
        }
        
        for(int num : newLost) {
            if (newReserve.contains(num-1)){
                newReserve.remove(Integer.valueOf(num-1));
                answer++;
            } 
            else if (newReserve.contains(num+1)){
                newReserve.remove(Integer.valueOf(num+1));
                answer++;
            }
        }
        
        return (n - newLost.size()) + answer;
    }
}