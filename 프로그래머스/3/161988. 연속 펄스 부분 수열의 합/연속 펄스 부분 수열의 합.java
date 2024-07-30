class Solution {
    public static long solution(int[] sequence) {
        int n = sequence.length;
        
        // 펄스 수열을 두 가지 형태로 변환
        long[] pulse1 = new long[n];
        long[] pulse2 = new long[n];
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                pulse1[i] = sequence[i];
                pulse2[i] = -sequence[i];
            } else {
                pulse1[i] = -sequence[i];
                pulse2[i] = sequence[i];
            }
        }
        
        // 두 펄스 수열에 대해 카데인 알고리즘을 적용하여 최대 부분 합을 구함
        long maxSum1 = kadane(pulse1);
        long maxSum2 = kadane(pulse2);
        
        // 두 수열 중 최대 부분 합을 반환
        return Math.max(maxSum1, maxSum2);
    }
    
    private static long kadane(long[] arr) {
        long maxEndingHere = arr[0];
        long maxSoFar = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
}