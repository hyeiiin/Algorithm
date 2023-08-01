import java.util.*;
import java.io.*;

public class Main_1244_김혜인 {
	static int n = 0;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine()); // 스위치 개수 입력
		int p = 0;
		arr = new int[n + 1]; // 스위치 상태 배열 선언 (인덱스 1부터 사용)
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 스위치 순서 입력 (1: 켜져있음, 0: 꺼져있음)
		}
		p = Integer.parseInt(br.readLine()); // 사람 수 입력 
		int gender = 0;
		for (int j = 0; j < p; j++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken()); // 성별 입력 (1: 남자, 2: 여자)
			if (gender == 1) {
				man(Integer.parseInt(st.nextToken())); // 남자일 경우 남자 메소드 호출
			} else
				woman(Integer.parseInt(st.nextToken())); // 여자일 경우 여자 메소드 호출
		}
		
		// 스위치 상태 출력
		for (int i = 1; i <= n; i++) {
			System.out.printf("%d ", arr[i]);
			if (i % 20 == 0)
				System.out.println(); // 20개씩 끊어서 출력
		}
		br.close();
	}

	// 여자가 누른 스위치와 대칭되는 스위치까지 상태 바꾸는 메소드
	private static void woman(int parseInt) {
		int xx = parseInt;
		if (arr[xx] == 1)
			arr[xx] = 0;
		else
			arr[xx] = 1;
		
		// 대칭되는 스위치 찾아서 상태 바꾸기
		for (int i = 1; i <= n / 2; i++) {
			if (xx - i < 1 || xx + i > n || arr[xx - i] != arr[xx + i])
				break;
			else {
				if (arr[xx - i] == 1) {
					arr[xx - i] = 0;
					arr[xx + i] = 0;
				} else {
					arr[xx - i] = 1;
					arr[xx + i] = 1;
				}
			}

		}

	}

	// 남자가 누른 스위치부터 시작해서 일정한 간격으로 상태 바꾸는 메소드
	private static void man(int parseInt) {
		int xy = parseInt; // 파라미터로 받아온거 xy에 담은 이유 -> xy 계속 커질거야. 3 -> 6 -> 9 -> 만약에 n보다 크면 멈춤
		while (xy <= n) { // 여기에 xy < n 이게 아니라 xy <= n 주의
			if (arr[xy] == 1) {
				arr[xy] = 0;
			} else
				arr[xy] = 1;

			xy += parseInt;
		}
	}

}
