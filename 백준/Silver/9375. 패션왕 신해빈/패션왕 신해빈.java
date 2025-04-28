import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        
        while (testCaseCount-- > 0) {
            int clothingCount = scanner.nextInt();
            Map<String, Integer> clothingMap = new HashMap<>();

            for (int i = 0; i < clothingCount; i++) {
                String clothingName = scanner.next();
                String clothingType = scanner.next();

                clothingMap.put(clothingType, clothingMap.getOrDefault(clothingType, 0) + 1);
            }

            int totalCombinations = 1;

            for (int count : clothingMap.values()) {
                totalCombinations *= (count + 1); // 해당 종류를 입지 않는 경우 포함
            }

            System.out.println(totalCombinations - 1); // 모두 안 입은 경우 제외
        }
    }
}
