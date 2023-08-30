import java.io.*;
import java.util.*;

public class Main_10971_김혜인 {
    static int N;  // 도시 수
    static int[][] W;  // 비용 행렬
    static int[][] dp;  // 동적계획법을 위한 배열
    static int INF = 10000000;  // 무한대를 의미하는 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 도시 수 입력받기 

        W = new int[N][N];  // 비용 행렬 초기화
        dp = new int[N][1 << N];  // dp 배열 초기화

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());  // 비용 입력
            }
        }

        // dp 배열을 -1로 초기화 한다. 
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < (1 << N); j++) {
                dp[i][j] = -1;
            }
        }

        // 처음과 끝이 0인 경우 최소 비용 출력하기 
        System.out.println(tsp(0, 1));
    }

    // 동적계획법 함수
    public static int tsp(int current, int visited) {
        if(visited == (1 << N) - 1) {  // 모든 도시를 방문한 경우
            if(W[current][0] == 0) return INF;
            return W[current][0];
        }

        // 이미 계산된 경우
        if(dp[current][visited] != -1) return dp[current][visited];

        dp[current][visited] = INF;  // 최소값을 찾기 위해 초기값을 무한대로 설정하기 

        for(int i = 0; i < N; i++) {
            // i 도시를 방문하지 않았고, i로 갈 수 있는 경우
            if((visited & (1 << i)) == 0 && W[current][i] != 0) {
                dp[current][visited] = Math.min(dp[current][visited],
                                                tsp(i, visited | (1 << i)) + W[current][i]);
            }
        }

        return dp[current][visited];
    }
}
