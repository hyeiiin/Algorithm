import java.io.BufferedReader;
import java.io.*;

public class Main_1992_김혜인 {
    static StringBuilder sb = new StringBuilder(); 
    static int[][] arr; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        // 입력 받은 문자열을 숫자로 변환하여 arr 배열에 저장
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
            	// 문자열 str의 j번째 문자를 가져와 '0'을 빼서 해당 문자가 '0'이면 0, '1'이면 1로 변환
        		// 그 결과를 arr[i][j]에 저장
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        // 쿼드 트리 arr 배열의 첫 인덱스[0,0]와 row/col 길이를 인수 넘겨주기
        quadTree(0, 0, n);
        System.out.println(sb);
    }

    // 쿼드 트리 메서드
    private static void quadTree(int x, int y, int size) {
        // 현재 영역의 색이 모두 같은 경우 출력 
        if (sameColor(x, y, size)) {
            sb.append(arr[x][y]); // 해당 색을 결과에 추가
        } else { // 색이 다르면 다시 분할하여 검색
            int halfSize = size / 2; // 현재 영역을 4분할하기 위해 크기를 반으로 줄임

            sb.append("("); // 괄호로 묶어서 표현
            quadTree(x, y, halfSize); // 왼쪽 위 영역
            quadTree(x, y + halfSize, halfSize); // 오른쪽 위 영역
            quadTree(x + halfSize, y, halfSize); // 왼쪽 아래 영역
            quadTree(x + halfSize, y + halfSize, halfSize); // 오른쪽 아래 영역
            sb.append(")");
        }
    }

    // 현재 영역의 색이 모두 같은지 확인
    private static boolean sameColor(int x, int y, int size) {
    	//2개 이상의 요소를 탐색해야 할 경우 첫번째 값을 기준으로 나머지 요소들과 비교
        int color = arr[x][y]; // 기준이 되는 첫 번째 색

        // 모든 요소를 기준 색과 비교
        for (int i = x; i < x + size; i++) { 
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != color) {
                    return false; // 다른 색이 발견되면 false 반환
                }
            }
        }
        return true; // 모든 요소가 같은 색이면 true 반환
    }
}
