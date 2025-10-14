import java.util.*;

class Solution {
    static class Block {
        int d, i, s;          
        int weight;           
    }
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        int maxBlocks = Math.min((minerals.length + 4) / 5, totalPicks);
        List<Block> blocks = new ArrayList<>(maxBlocks);

        for (int idx = 0; idx < minerals.length && blocks.size() < maxBlocks; idx += 5) {
            Block b = new Block();
            for (int k = idx; k < Math.min(idx + 5, minerals.length); k++) {
                String z = minerals[k];
                if (z.equals("diamond")) b.d++;
                else if (z.equals("iron")) b.i++;
                else b.s++;
            }
            b.weight = b.d * 25 + b.i * 5 + b.s; 
            blocks.add(b);
        }

        blocks.sort((x, y) -> y.weight - x.weight);

        int fatigue = 0;
        for (Block b : blocks) {
            int tool;
            if (picks[0] > 0) tool = 0;
            else if (picks[1] > 0) tool = 1;
            else if (picks[2] > 0) tool = 2;
            else break;

            if (tool == 0) {               
                fatigue += b.d + b.i + b.s;
                picks[0]--;
            } else if (tool == 1) {       
                fatigue += b.d * 5 + b.i + b.s;
                picks[1]--;
            } else {                        
                fatigue += b.d * 25 + b.i * 5 + b.s;
                picks[2]--;
            }
        }
        return fatigue;
    }
}
