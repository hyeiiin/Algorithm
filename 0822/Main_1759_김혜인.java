import java.io.*;
import java.util.*;

public class Main_1759_김혜인 {
	
	public static int L, C; // 암호의 길이(L), 사용 가능한 알파벳 개수(C)
	public static char[] alphabets; // 사용 가능한 알파벳
	public static boolean[] visited; // 방문 여부 체크하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 암호의 길이 입력 받기 
		C = Integer.parseInt(st.nextToken()); // 사용 가능한 알파벳 개수 입력 받기 

		alphabets = new char[C];
		visited = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabets[i] = st.nextToken().charAt(0); // 사용 가능한 알파벳 입력 받기 
		}

		// 알파벳을 사전 순으로 정렬
		Arrays.sort(alphabets);

		// 조합 구하고, 가능한 암호 출력 
		combination("", 0, 0, 0);

	}

	public static void combination(String password, int start, int M, int J) { // M 모음 J 자음 
		// 암호의 길이가 L이고 최소 한 개의 모음과 두 개의 자음을 포함하면 출력
		if (password.length() == L && M >= 1 && J >= 2) {
			System.out.println(password);
			return;
		}
		
		for (int i = start; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true; // 현재 알파벳을 방문 처리 
				// 현재 알파벳이 모음인지 자음인지 판단하고, 카운트 증가시키기 
				if (alphabets[i] == 'a' || alphabets[i] == 'e' || alphabets[i] == 'i' || alphabets[i] == 'o' || alphabets[i] == 'u') {
					combination(password + alphabets[i], i + 1, M + 1, J);
				} else { // 자음이면 자음 카운트 증가 
					combination(password + alphabets[i], i + 1, M, J + 1);
				}
				visited[i] = false; // 현재 알파벳 방문 해제 
			}
		}
	}
}
