import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] ip = input.split(":");
        String[] ans = new String[8];
        int index = 0;
        boolean flag = false; // "::"를 처리하기 위한 플래그

        for (int i = 0; i < 8; i++) {
            ans[i] = "0000"; // 기본적으로 모든 자리 "0000"으로 초기화
        }

        for (int i = 0; i < ip.length; i++) {
            if (ip[i].length() == 4) {
                ans[index] = ip[i];
                index++;
            } else if (ip[i].length() > 0) {
                ans[index] = "0000".substring(ip[i].length()) + ip[i]; // 4자리로 맞추기
                index++;
            } else { // "::" 처리
                if (!flag) {
                    index += (8 - ip.length + 1); // "::"가 나타났을 때, 중간의 0000을 채움
                    flag = true;
                } else {
                    ans[index] = "0000";
                    index++;
                }
            }
        }

        // 출력
        for (int i = 0; i < 7; i++) {
            System.out.print(ans[i] + ":");
        }
        System.out.println(ans[7]);

        sc.close();
    }
}
