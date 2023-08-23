
import java.io.*;
import java.util.*;

public class Solution_1238_김혜인 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 1; t <= 10; t++) { // 10개의 테스트 케이스 반복
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 데이터의 길이
            int start = Integer.parseInt(st.nextToken()); // 시작점
            int[][] map = new int[101][101]; // 연락망 표현을 위한 배열
            boolean[] visited = new boolean[101]; // 방문 체크 

            // 연락망 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                map[from][to] = 1;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            visited[start] = true;
            int last = start; // 마지막에 연락받은 사람의 번호

            while (!queue.isEmpty()) { // BFS 시작
                int size = queue.size();
                int max = 0;

                for (int i = 0; i < size; i++) {
                    int current = queue.poll(); // 현재 연락 대상
                    for (int j = 1; j <= 100; j++) {
                        // 연락이 가능하고 방문하지 않았던 대상에 연락
                        if (map[current][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.offer(j);
                            if (j > max) {
                                max = j; // 해당 단계에서 가장 큰 번호 기록
                            }
                        }
                    }
                }

                if (max != 0) {
                    last = max; // 마지막에 연락한 번호 
                }
            }
           // BFS(너비 우선 탐색)를 사용하여 연락이 가능한 방향으로 진행, 마지막에 도달한 가장 큰 숫자를 반환한다! 
            System.out.println("#" + t + " " + last); // 결과 출력
        }
    }
}
