import java.io.*;
import java.util.*;

public class Main_1547_김혜인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine()); // 컵의 위치를 바꾼 횟수 
		int ballPosition = 1; // 처음 공이 있는 컵의 번호

		// M번의 컵 위치 바꾸기 
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 바꿀 첫 번째 컵의 번호
			int Y = Integer.parseInt(st.nextToken()); // 바꿀 두 번째 컵의 번호

			// 공이 있는 컵의 위치가 바뀌면
			// 바꿀 두 컵 중 하나가 현재 공 위치와 같다면 공 위치 변경
			if (X == ballPosition) {
				ballPosition = Y;
			} else if (Y == ballPosition) {
				ballPosition = X;
			}
		}

		System.out.println(ballPosition); 
	}
}
