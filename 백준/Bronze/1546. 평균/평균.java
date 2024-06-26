import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 시험 과목 수
        sc.nextLine(); // nextInt() 후 남은 개행 문자 제거
        String[] scores = sc.nextLine().split(" "); // 처음 점수 저장 배열
        int M = 0; // 처음 점수 최댓값
        double sum = 0; // 새로운 점수들 합

        // 정수 배열로 변환
        int[] scores1 = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            scores1[i] = Integer.parseInt(scores[i]);
        }

        // 처음 점수 최댓값 추출
        for (int i = 0; i < N; i++) {
            M = Math.max(scores1[i], M);
        }

        // 새로운 점수들 합
        for (int i = 0; i < N; i++) {
            sum += ((double)scores1[i]/M)*100;
        }

        System.out.println(sum/N);
    }
}
