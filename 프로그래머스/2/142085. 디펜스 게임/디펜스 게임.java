class Solution {
    public int solution(int n, int k, int[] enemy) {
        java.util.PriorityQueue<Integer> inv = new java.util.PriorityQueue<>(); 
        for (int i = 0; i < enemy.length; i++) {
            inv.add(enemy[i]);
            if (inv.size() > k) {
                n -= inv.poll();  
            }
            if (n < 0) return i;  
        }
        return enemy.length;
    }
}
