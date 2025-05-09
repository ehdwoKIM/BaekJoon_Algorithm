import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int myArr[];
    static int checkArr[];
    static int checkSecret;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Result = 0;
        checkArr = new int[4]; // 비밀번호 체크 배열
        myArr = new int[4]; // 현재상태 배열
        char A[] = new char[S];
        checkSecret = 0; // 4개중에 몇개가 비밀번호 조건에 만족하는지

        A = bf.readLine().toCharArray();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkSecret++; // 비밀번호 체크 배열의 요소가 0일 경우 이미 만족하므로 미리 추가한다
            }
        }

        // P 길이의 부분 문자열 처음 받을때 현재상태 배열에 세팅
        for (int i = 0; i < P; i++) {
            Add(A[i]);
        }

        if (checkSecret == 4) Result++;


        //슬라이딩 윈도우 (위의 첫 세팅 이후 한칸 이동한 상태부터 시작)
        for (int i = P; i < S; i++) {
            // j가 윈도우의 맨 왼쪽 포인터, i는 맨 오른쪽 포인터
            int j = i- P;
            Add(A[i]);
            Remove(A[j]);
            if (checkSecret == 4) Result++;
        }

        System.out.println(Result);
        bf.close();
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0]) checkSecret--;
                myArr[0]--;
                break;
            case 'C':
                if (myArr[1] == checkArr[1]) checkSecret--;
                myArr[1]--;
                break;
            case 'G':
                if (myArr[2] == checkArr[2]) checkSecret--;
                myArr[2]--;
                break;
            case 'T':
                if (myArr[3] == checkArr[3]) checkSecret--;
                myArr[3]--;
                break;
        }
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0]) checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1]) checkSecret++;
                break;
            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2]) checkSecret++;
                break;
            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3]) checkSecret++;
                break;
        }
    }
}
