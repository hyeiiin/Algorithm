import java.io.*;
import java.util.*;

public class SWEA_5656_김혜인 {
    static int T, W, H, minBricks;
    static int[] pos;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken()); // 던질 횟수
            W = Integer.parseInt(st.nextToken()); // 판의 너비
            H = Integer.parseInt(st.nextToken()); // 판의 높이

            map = new int[H][W];
            pos = new int[T];

            minBricks = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()); // 판 채우기
                }
            }
            setBall(0);
            sb.append("#").append(tc).append(" ").append(minBricks).append("\n");
        }
        System.out.println(sb);
    }

    // 순열을 이용해 공의 위치 결정
    public static void setBall(int cnt) {
        if (cnt == T) {
            start();
            return;
        }
        for (int i = 0; i < W; i++) {
            pos[cnt] = i;
            setBall(cnt + 1);
        }
    }

    // 결정된 위치로 게임 시작
    public static void start() {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                temp[i][j] = map[i][j]; // 원본 판 복사
            }
        }

        for (int i = 0; i < T; i++) {
            int x = -1;
            int y = pos[i];
            for (int j = 0; j < H; j++) {
                if (temp[j][y] != 0) { // 컬럼에서 첫 번째 벽돌 찾기
                    x = j;
                    break;
                }
            }
            if (x == -1) continue; // 벽돌이 없으면 다음 턴으로
            breakBricks(x, y, temp); // 벽돌 부수기
            dropBricks(temp); // 남은 벽돌 정리
        }

        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (temp[i][j] != 0) count++; // 남아있는 벽돌 수 세기
            }
        }
        minBricks = Math.min(count, minBricks); // 최소 벽돌 수 업데이트
    }

    // 벽돌의 힘에 따라 벽돌 부수기
    public static void breakBricks(int x, int y, int[][] temp) {
        int num = temp[x][y];
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { -1, 1, 0, 0 };

        temp[x][y] = 0; // 초기 벽돌 부수기
        if (num == 1) return;

        // 주변 벽돌 부수기
        for (int i = 0; i < 4; i++) {
            int nx = x, ny = y;
            for (int j = 0; j < num - 1; j++) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) break;
                if (temp[nx][ny] == 0) continue;
                if (temp[nx][ny] == 1) {
                    temp[nx][ny] = 0;
                } else {
                    breakBricks(nx, ny, temp); // 큰 벽돌에 대한 재귀 호출
                }
            }
        }
    }

    // 부순 후의 벽돌 정리
