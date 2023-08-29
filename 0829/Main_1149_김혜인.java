import java.io.*;
import java.util.*;

public class Main_1149_김혜인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 집의 개수 N 입력받기 
 
		int[][] dp = new int[N+1][3]; // dp배열 선언 , dp[i][j]는 i번째 집까지 고려했을 때 j색을 선택한 경우의 최소 비용
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+r;
			// i번째 집을 빨간색으로 칠하려면 바로 앞 i-1번째 집은 빨간색이 아니어야 함
			// i-1번째 집이 초록색이나 파랑색으로 칠해진 경우에 
			// 비용이 적게 드는 것을 선택하고 + i번째 집을 빨간색으로 칠하는 비용을 더해준다.
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+r; // 현재 집을 빨강으로 칠할 때
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+g; // 현재 집을 초록으로 칠할 때
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+b; // 현재 집을 파랑으로 칠할 때
		}
		
		// 마지막 집 N을 칠할 때 각 색의 최소 비용 중에서 가장 작은 값을 출력
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}

}
