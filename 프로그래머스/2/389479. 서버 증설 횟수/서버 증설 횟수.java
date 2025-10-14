class Solution {
    public int solution(int[] players, int m, int k) {
        int add = 0;       
        int active = 0;       
        int[] expire = new int[24 + k + 1]; // 각 시각에 반납될 서버 대수

        for (int i = 0; i < 24; i++) {
            // 만료 반영
            active -= expire[i];

            // 필요 서버 수
            int need = (players[i] < m) ? 0 : (players[i] / m);

            if (active < need) {
                int delta = need - active;   
                add += delta;                
                active += delta;
                expire[i + k] += delta;      
            }
        }
        return add;
    }
}
