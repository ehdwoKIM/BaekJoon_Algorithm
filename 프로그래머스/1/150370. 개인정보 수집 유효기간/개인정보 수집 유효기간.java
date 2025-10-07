import java.util.*;
class Solution {
    private int toDays(String s){
        String[] p = s.split("\\.");
        int y = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        int d = Integer.parseInt(p[2]);
        return y*12*28 + m*28 + d;
    }
    public int[] solution(String today, String[] terms, String[] privacies) {
        int now = toDays(today);
        Map<String,Integer> term = new HashMap<>();
        for(String t: terms){
            String[] sp = t.split(" ");
            term.put(sp[0], Integer.parseInt(sp[1]));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<privacies.length;i++){
            String[] sp = privacies[i].split(" ");
            int start = toDays(sp[0]);
            int months = term.get(sp[1]);
            int expireNext = start + months*28; // 만료일 다음날(= 유효 끝 + 1)
            if(now >= expireNext) ans.add(i+1); // 오늘이 만료 다음날 이상이면 파기
        }
        return ans.stream().mapToInt(x->x).toArray();
    }
}
