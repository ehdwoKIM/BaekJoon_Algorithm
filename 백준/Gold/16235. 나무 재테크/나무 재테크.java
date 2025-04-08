import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, nutrient;
    static ArrayList<Integer>[][] trees;

    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 땅 크기
        M = Integer.parseInt(st.nextToken());  // 초기 나무 수
        K = Integer.parseInt(st.nextToken());  // K년

        A = new int[N][N];             // 겨울에 추가되는 양분
        nutrient = new int[N][N];      // 현재 양분
        trees = new ArrayList[N][N];   // 각 칸의 나무 리스트

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;              // 초기 양분은 5
                trees[i][j] = new ArrayList<>(); // 리스트 초기화
            }
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);  // 좌표에 나무 나이 추가
        }

        while(K-- > 0) {
            // ======= 봄 =======
            ArrayList<int[]> dead = new ArrayList<>();
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if (trees[i][j].isEmpty()) continue;
                    Collections.sort(trees[i][j]); // 나이순 정렬 (어린 나무 먼저)

                    ArrayList<Integer> alive = new ArrayList<>();
                    for(int k=0;k<trees[i][j].size();k++) {
                        int age = trees[i][j].get(k);
                        if(nutrient[i][j] >= age) {
                            nutrient[i][j] -= age;
                            alive.add(age + 1); // 나무 성장
                        } else {
                            dead.add(new int[]{i, j, age}); // 죽은 나무 기록
                        }
                    }
                    trees[i][j] = alive; // 살아남은 나무만 갱신
                }
            }

            // ======= 여름 =======
            for(int[] d : dead) {
                int x = d[0], y = d[1], age = d[2];
                nutrient[x][y] += age / 2; // 나이 / 2 만큼 양분
            }

            // ======= 가을 =======
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    for(int age : trees[i][j]) {
                        if(age % 5 != 0) continue; // 번식 조건
                        for(int dir=0;dir<8;dir++) {
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];
                            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                            trees[nx][ny].add(1); // 새로운 나무
                        }
                    }
                }
            }

            // ======= 겨울 =======
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    nutrient[i][j] += A[i][j]; // 양분 추가
                }
            }
        }

        // 나무 개수 세기
        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                cnt += trees[i][j].size();
            }
        }
        System.out.println(cnt);
    }
}
