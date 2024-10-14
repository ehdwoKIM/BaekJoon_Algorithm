import java.io.*;
import java.util.StringTokenizer;

class Egg {
    public Egg(int durability, int weight) {
        this.durability = durability;
        this.weight = weight;
        isBroken = false;
    }
    // 계란이 부셔졌는지
    boolean isBroken;
    // 계란의 내구도
    int durability;
    // 계란의 무게
    int weight;

    // 계란 두개를 부딪히는 경우
    public int hittingTwoEggs(Egg egg2) {
        int numOfBrokenEggs = 0;
        this.durability -= egg2.weight;
        egg2.durability -= this.weight;

        if (this.durability <= 0) {
            this.isBroken = true;
            numOfBrokenEggs++;
        }
        if (egg2.durability <= 0) {
            egg2.isBroken = true;
            numOfBrokenEggs++;
        }

        return numOfBrokenEggs;
    }

    // 계란 부딪힌 걸 없던 걸로 하기
    public int recoveryTwoEggs(Egg egg2) {
        int numOfRecoveryEggs = 0;
        boolean wasEgg1Broken = this.isBroken;
        boolean wasEgg2Broken = egg2.isBroken;
        this.durability += egg2.weight;
        egg2.durability += this.weight;

        if (this.durability > 0 && wasEgg1Broken) {
            this.isBroken = false;
            numOfRecoveryEggs++;
        }
        if (egg2.durability > 0 && wasEgg2Broken) {
            egg2.isBroken = false;
            numOfRecoveryEggs++;
        }

        return numOfRecoveryEggs;
    }
}

public class Main {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cntOfEggs = Integer.parseInt(br.readLine());
        Egg[] arrOfEggs = new Egg[cntOfEggs];
        for (int i = 0; i < cntOfEggs; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arrOfEggs[i] = new Egg(durability,weight);
        }

        int idxOfGrabbedEgg = 0;
        int numOfBrokenEggs = 0;
        findCaseMaxBrokenEggsNum(arrOfEggs, idxOfGrabbedEgg, numOfBrokenEggs);
        System.out.println(answer);
    }

    private static void findCaseMaxBrokenEggsNum(Egg[] arrOfEggs, int idxOfGrabbedEgg, int numOfBrokenEggs) {
        // 마지막 계란을 넘어섰거나, 전체 계란 개수-1보다 많이 깼으면 더 이상 깰 수 없음.(1개 남았거나, 다 깼으니까 못깸)
        if (idxOfGrabbedEgg > arrOfEggs.length-1 || numOfBrokenEggs >= arrOfEggs.length-1) {
            answer = Math.max(numOfBrokenEggs,answer);
            return;
        } else if (arrOfEggs[idxOfGrabbedEgg].isBroken) {
            findCaseMaxBrokenEggsNum(arrOfEggs, idxOfGrabbedEgg+1, numOfBrokenEggs);
            return;
        }

        for (int target = 0; target < arrOfEggs.length; target++) {
            // 부딪힐 계란이 자기 자신이거나, 이미 깨진 계란이면 다음 계란으로 넘어감
            if (idxOfGrabbedEgg == target || arrOfEggs[target].isBroken) {
                continue;
            }
            // 계란 두개 부딪힘.
            numOfBrokenEggs += arrOfEggs[idxOfGrabbedEgg].hittingTwoEggs(arrOfEggs[target]);
            findCaseMaxBrokenEggsNum(arrOfEggs, idxOfGrabbedEgg+1, numOfBrokenEggs);
            // 계란 부딪힌 거 수리
            numOfBrokenEggs -= arrOfEggs[idxOfGrabbedEgg].recoveryTwoEggs(arrOfEggs[target]);
        }
    }
}
