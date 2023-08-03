import java.util.*;
import java.io.*;

public class Solution_2001_김혜인 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine()); // 테스트 케이스를 입력받는다.
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스 순회
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N]; //(0,0)~(N-1,N-1)
			
			for (int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					}
			}
			
			int maxKill = 0; //최대로 죽인 파리 수 
			
			//완전 탐색(브루트포스 알고리즘) 이용 -> N과 M의 범위가 작기 때문에 
			//N과 M 둘 다 15 이하이므로 완저너 탐색해도 괜찮다.
			for(int i=0; i <=N-M; i++) {
				for(int j=0; j <=N-M; j++) {
					int sum = 0;
					for(int k=0;k<M;k++) {
						for(int s=0;s<M; s++) {
							sum +=map[i+k][j+s];
						}
					}
					//최대로 죽인 파리 수 갱신하기 
					maxKill = Math.max(maxKill, sum);
				}
			}
			sb.append("#" + tc + " " + maxKill).append("\n");
			
		}
		System.out.println(sb);
	}

}
