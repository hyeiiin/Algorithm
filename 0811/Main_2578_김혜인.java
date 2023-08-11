import java.io.*;
import java.util.*;

public class Main_2578_김혜인 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] arr = new int[5][5]; 
		
		// 빙고판 입력 받기
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int calledNumberCount = 0; // 부른 숫자의 수
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int calledNumber = Integer.parseInt(st.nextToken()); // 부른 숫자
				calledNumberCount++;

				// 빙고판에서 해당 숫자 찾아서 0으로 변경 
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 5; l++) {
						if (arr[k][l] == calledNumber) {
							arr[k][l] = 0;
						}
					}
				}

				// 빙고 체크
				int count = 0;
				// 가로 줄 검사
				for (int k = 0; k < 5; k++) {
					int rowSum = 0;
					for (int l = 0; l < 5; l++) {
						rowSum += arr[k][l];
					}
					if (rowSum == 0) count++;
				}

				// 세로 줄 검사
				for (int k = 0; k < 5; k++) {
					int colSum = 0;
					for (int l = 0; l < 5; l++) {
						colSum += arr[l][k];
					}
					if (colSum == 0) count++;
				}

				// 대각선 검사
				if (arr[0][0] + arr[1][1] + arr[2][2] + arr[3][3] + arr[4][4] == 0) count++;
				if (arr[0][4] + arr[1][3] + arr[2][2] + arr[3][1] + arr[4][0] == 0) count++;

				// 빙고가 3개 이상이면 결과 출력 후 종료
				if (count >= 3) {
					System.out.println(calledNumberCount);
					return;
				}
			}
		}
	}
}
