import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    //광물의 위치 관련 정보 클래스
    static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    //광석 강도 저장 배열
    static int[][] mineral;
    static int N, M, K;
    static int[] dr = {-1, 1, 0, 0};	//상하좌우 y 변경값
    static int[] dc = {0, 0, -1, 1};	//상하좌우 x 변경값
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //결과값 출력하는 BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        mineral = new int[N][M];
        //광석 정보 저장 및 최대, 최소값 구하기
        for(int i=N-1;i>=0;i--){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                mineral[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min,mineral[i][j]);
                max = Math.max(max,mineral[i][j]);
            }
        }
        //이분 탐색 진행으로 최소 성능 구하기
        int result = search(min, max);
        //최소 성능 BufferedWriter 저장
        bw.write(String.valueOf(result));
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //이분 탐색을 통해 채굴기 최소 성능의 값을 구하는 함수
    static int search(int min, int max){
        //start : 강도 최소 값
        //end : 강도 최대 값
        int start = min;
        int end = max;
        //이분 탐색 진행
        while(start <= end){
            int mid = (start + end) / 2;
            //강도가 mid일 때 캐낼 수 있는 광석이 K개 이상일 때
            if(check(mid)){
                end = mid - 1;
            }else{	//강도가 mid일 때 캐낼 수 있는 광석이 K개 미만일 때
                start = mid + 1;
            }
        }
        return start;	//최소 성능 정보 반환
    }
    //성능에 따른 광물 채굴 가능한 개수를 BFS으로 탐색하는 함수
    static boolean check(int mid){
        Queue<Pair> q = new LinkedList<>();
        //광물 캐낸 여부 배열
        boolean[][] visited = new boolean[N][M];
        //초기 항상 공기에 맞닿는 부분 Queue 저장 및 캐낸 여부 변경
        for(int i=0;i<N;i++){
            if(mineral[i][0] <= mid){
                q.offer(new Pair(i,0));
                visited[i][0] = true;
            }
            if(mineral[i][M-1] <= mid){
                q.offer(new Pair(i,M-1));
                visited[i][M-1] = true;
            }
        }
        for(int i=1;i<M-1;i++){
            if(mineral[N-1][i] <= mid){
                q.offer(new Pair(N-1,i));
                visited[N-1][i] = true;
            }
        }
        int cnt = q.size();
        //BFS진행
        while(!q.isEmpty()){
            Pair cur = q.poll();
            //상하좌우 공기가 있는 부분 찾기
            for(int i=0;i<4;i++){
                int tempR = cur.x + dr[i];
                int tempC = cur.y + dc[i];
                //광석이면서 공기를 맞닿을 때
                if(inSpace(tempR,tempC) && !visited[tempR][tempC] && mineral[tempR][tempC] <= mid){
                    q.offer(new Pair(tempR,tempC));
                    visited[tempR][tempC] = true;
                    cnt++;
                }
            }
        }
        //채굴한 개수가 K개 이상일 때 : True, 미만일 때 : False
        return cnt >= K;
    }
    //탐색하려는 공간이 광석이 존재하는 곳인지 확인하는 함수
    static boolean inSpace(int r, int c){
        if(r >= 0 && c >= 0 && r < N && c < M){
            return true;
        }
        return false;
    }
}