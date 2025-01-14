import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dnaCount = scanner.nextInt();
        int dnaLength = scanner.nextInt();
        scanner.nextLine();

        String[] dnaStrings = new String[dnaCount];
        for (int i = 0; i < dnaCount; i++) {
            dnaStrings[i] = scanner.nextLine();
        }

        // 각 위치별 문자빈도
        int[][] frequency = new int[dnaLength][4]; // A, C, G, T -> 0, 1, 2, 3 매핑
        for (String dna : dnaStrings) {
            for (int i = 0; i < dnaLength; i++) {
                char nucleotide = dna.charAt(i);
                switch (nucleotide) {
                    case 'A': frequency[i][0]++; break;
                    case 'C': frequency[i][1]++; break;
                    case 'G': frequency[i][2]++; break;
                    case 'T': frequency[i][3]++; break;
                }
            }
        }

        // 미니멈 Hamming Distance 만드는 DNA 문자열 & 거리
        StringBuilder consensusDNA = new StringBuilder();
        int totalHammingDistance = 0;

        for (int i = 0; i < dnaLength; i++) {
            int maxFrequencyIndex = 0;
            int maxFrequency = frequency[i][0];

            for (int j = 1; j < 4; j++) {
                if (frequency[i][j] > maxFrequency) {
                    maxFrequencyIndex = j;
                    maxFrequency = frequency[i][j];
                }
            }
            // 최빈값 문자
            char selectedNucleotide;
            switch (maxFrequencyIndex) {
                case 0:
                    selectedNucleotide = 'A';
                    break;
                case 1:
                    selectedNucleotide = 'C';
                    break;
                case 2:
                    selectedNucleotide = 'G';
                    break;
                case 3:
                    selectedNucleotide = 'T';
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + maxFrequencyIndex);
            }
            consensusDNA.append(selectedNucleotide);
            totalHammingDistance += dnaCount - maxFrequency;
        }
        System.out.println(consensusDNA.toString());
        System.out.println(totalHammingDistance);
    }
}
