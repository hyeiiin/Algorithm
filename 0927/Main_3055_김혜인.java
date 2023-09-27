
import java.util.*;
import java.io.*;

public class Main_3055_김혜인 {

    // 상하좌우 이동을 위한 방향 배열
    static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행 수
        int C = Integer.parseInt(st.nextToken()); // 열 수

        char[][] forest = new char[R][C]; // 숲 지도를 나타내는 배열

        // 고슴도치 위치와 물의 위치를 저장하는 큐, 방문 여부를 나타내는 배열
        Queue<int[]> hedgehogPosition = new LinkedList<>();
        Queue<int[]> waterQueue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        // 숲 지도 입력
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                forest[i][j] = input.charAt(j);

                if (forest[i][j] == 'S') {
                    // 고슴도치의 초기 위치를 큐에 추가하고, 해당 위치는 빈 곳으로 표시하고 방문 처리
                    hedgehogPosition.add(new int[] { i, j });
                    forest[i][j] = '.';
                    visited[i][j] = true;

                } else if (forest[i][j] == '*') {
                    // 물의 초기 위치를 큐에 추가
                    waterQueue.add(new int[] { i, j });
                }
            }
        }

        int count = 0; // 시간 카운트

        while (!hedgehogPosition.isEmpty()) {

            count++; // 시간 증가

            // 물 이동
            int waterSize = waterQueue.size();
            while (waterSize-- > 0) {

                int[] water = waterQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int wr = water[0] + move[i][0];
                    int wc = water[1] + move[i][1];

                    // 범위를 벗어나거나, 이미 물이거나, 돌(X)인 경우는 이동하지 않음
                    if (wr < 0 || wr >= R || wc < 0 || wc >= C || forest[wr][wc] != '.') {
                        continue;
                    }

                    // 물로 퍼뜨리고 큐에 추가
                    forest[wr][wc] = '*';
                    waterQueue.add(new int[] { wr, wc });
                }
            }

            // 고슴도치 이동
            int hedgehogSize = hedgehogPosition.size();
            while (hedgehogSize-- > 0) {

                int[] hedgehog = hedgehogPosition.poll();

                for (int i = 0; i < 4; i++) {
                    int hr = hedgehog[0] + move[i][0];
                    int hc = hedgehog[1] + move[i][1];

                    // 범위를 벗어나거나, 이미 방문했거나, 물이거나, 돌(X)인 경우는 이동하지 않음
                    if (hr < 0 || hr >= R || hc < 0 || hc >= C || visited[hr][hc] || forest[hr][hc] == '*' || forest[hr][hc] == 'X') {
                        continue;
                    }

                    // 목적지(D)에 도착하면 결과 출력하고 종료
                    if (forest[hr][hc] == 'D') {
                        System.out.println(count);
                        return;
                    }

                    // 방문 표시하고 고슴도치 위치 큐에 추가
                    visited[hr][hc] = true;
                    hedgehogPosition.add(new int[] { hr, hc });
                }
            }
        }

        // 고슴도치가 목적지에 도달하지 못한 경우
        System.out.println("KAKTUS");
    }
}
