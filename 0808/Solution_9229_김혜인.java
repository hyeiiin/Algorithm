import java.util.*;
import java.io.*;

public class Solution_9229_김혜인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		// 테스트 케이스
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 과자 봉지의 무게 저장하는 배열 생성
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int[] snacks = new int[N];
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st2.nextToken());
			}

			// 최대 무게 합 불가능하면 -1 
			int maxWeight = -1;

			// 모든 과자 봉지 조합
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					// 현재 조합 무게 합
					int currentWeight = snacks[i] + snacks[j];

					// 무게 합이 M을 초과하지 않는 경우 최대 무게 합
					if (currentWeight <= M) {
						maxWeight = Math.max(maxWeight, currentWeight);
					}
				}
			}

			System.out.println("#" + t + " " + maxWeight);
		}
	}
}
