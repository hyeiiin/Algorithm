import java.util.*;
import java.io.*;

public class Main_2563_김혜인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int total = 0; // 검은색 영역 총 넓이
        int N = Integer.parseInt(br.readLine()); // 색종이의 개수
        boolean[][] paper = new boolean[101][101]; // 도화지 (true: 색종이 붙을 때)

        // N개의 색종이 반복 
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 색종이의 왼쪽 변 위치
            int y = Integer.parseInt(st.nextToken()); // 색종이의 아래쪽 변 위치
            total += placePaper(paper, x, y); // 색종이 붙이고, 해당 영역의 넓이 더하기 
        }

        System.out.print(total); // 총 넓이 
    }

    // 색종이 붙이는 함수, 붙인 색종이의 넓이 반환 
    private static int placePaper(boolean[][] paper, int x, int y) {
        int area = 0; // 현재 색종이가 붙은 영역의 넓이 
        for (int j = x; j < x + 10; j++) {
            for (int k = y; k < y + 10; k++) {
                if (!paper[j][k]) { // 해당 위치에 색종이가 아직 붙지 않았다면
                    paper[j][k] = true; // 색종이 붙이기 
                    area++; // 넓이 증가 
                }
            }
        }
        return area; // 현재 색종이가 붙은 영역의 넓이 반환 
    }
}
