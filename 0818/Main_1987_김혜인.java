import java.util.*;
import java.io.*;

public class Main_1987_김혜인 {
	static int R; // 지도의 세로 길이
	static int C; // 지도의 가로 길이
	static int[][] map; // 지도 배열
	static boolean[] visit = new boolean[26]; // 알파벳 방문 여부 체크 
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer = 0; // 방문할 수 있는 최대 칸 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		// 지도에 글자 정보를 저장
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A'; // 알파벳을 숫자로 변환하여 저장
			}
		}

		dfs(0, 0, 0); // DFS 시작
		System.out.println(answer); // 결과 출력
	}

	public static void dfs(int x, int y, int count) {
		if (visit[map[x][y]]) { // 현재 위치의 알파벳을 방문한 적이 있다면
			answer = Math.max(answer, count); // 최대 칸 수 만들기 
			return;
		} else {
			visit[map[x][y]] = true; // 현재 위치의 알파벳을 방문했다고 표시
			for (int i = 0; i < 4; i++) { // 상하좌우로 이동 시도
				int cx = x + dx[i];
				int cy = y + dy[i];

				if (cx >= 0 && cy >= 0 && cx < R && cy < C) { // 지도 범위 내에 있는 경우
					dfs(cx, cy, count + 1); // 다음 위치로 DFS (여기서 count++ 이 아님 주의하기)
				}
			}
			visit[map[x][y]] = false; // 현재 위치의 알파벳 방문 여부를 취소 (백트래킹)
		}
	}
}
