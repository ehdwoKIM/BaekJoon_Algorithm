import java.util.Arrays;
class Solution {
    public static int solution(int[][] targets) {
        // 폭격 미사일 구간을 끝나는 시점을 기준으로 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        // 요격 미사일의 개수를 세기 위한 변수
        int interceptors = 0;
        // 현재 요격 미사일이 발사된 위치
        int currentInterceptorEnd = Integer.MIN_VALUE;

        // 각 폭격 미사일 구간을 순회
        for (int[] target : targets) {
            // 현재 요격 미사일이 해당 구간을 커버할 수 없는 경우
            if (currentInterceptorEnd <= target[0]) {
                // 새로운 요격 미사일을 발사
                interceptors++;
                // 새로운 요격 미사일의 위치는 현재 구간의 끝나는 시점
                currentInterceptorEnd = target[1];
            }
        }

        return interceptors;
    }
}