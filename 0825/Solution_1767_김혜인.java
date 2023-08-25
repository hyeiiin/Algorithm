import java.io.*;
import java.util.*;

public class Solution_1767_김혜인 {
    static int N, totalCore, maxLength, minWire;
    static int[][] map;
    static ArrayList<int[]> core;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력 받기

        // 각 테스트 케이스
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine()); // 그리드 크기 N 입력 받기
            map = new int[N][N];
            core = new ArrayList<>();
            
            // 그리드 정보 읽고 코어 위치 저장
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(line[j]);
                    // 가장자리에 있지 않은 코어 위치 저장
                    if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                        core.add(new int[]{i, j});
                    }
                }
            }

            totalCore = core.size(); // 코어 총 개수
            maxLength = 0; // 최대 연결 코어 수
            minWire = Integer.MAX_VALUE; // 최소 전선 길이

            dfs(0, 0, 0); // 깊이 우선 탐색 시작

            System.out.println("#" + t + " " + minWire); // 결과 출력
        }
    }

    // 깊이 우선 탐색으로 모든 코어 시도
    static void dfs(int idx, int length, int connected) {
        // 모든 코어 탐색 마쳤으면 결과 비교
        if (idx == totalCore) {
            if (connected > maxLength) { // 더 많은 코어 연결한 경우
                maxLength = connected;
                minWire = length;
            } else if (connected == maxLength) { // 같은 수의 코어 연결한 경우
                minWire = Math.min(minWire, length); // 더 짧은 전선을 선택
            }
            return;
        }

        int[] currentCore = core.get(idx); // 현재 코어 위치
        int x = currentCore[0];
        int y = currentCore[1];

        // 4 방향으로 전선 연결
        for (int d = 0; d < 4; d++) {
            int nx = x, ny = y, wire = 0;
            boolean canConnect = true;

            // 해당 방향으로 전선 놓을 수 있는지 확인
            while (true) {
                nx += dx[d];
                ny += dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) break; // 그리드 밖으로 나가면 중지
                if (map[nx][ny] != 0) { // 다른 코어나 전선이 있으면 연결할 수 없다.
                    canConnect = false;
                    break;
                }
                wire++; // 전선 길이 증가
            }

            // 연결 가능한 경우
            if (canConnect) {
                int tempX = x, tempY = y;
                // 전선 놓기
                while (true) {
                    tempX += dx[d];
                    tempY += dy[d];
                    if (tempX < 0 || tempY < 0 || tempX >= N || tempY >= N) break;
                    map[tempX][tempY] = 2;
                }
                // 다음 코어로 재귀 호출
                dfs(idx + 1, length + wire, connected + 1);
                // 원래 상태로 되돌린다 (백트래킹)
                tempX = x;
                tempY = y;
                while (true) {
                    tempX += dx[d];
                    tempY += dy[d];
                    if (tempX < 0 || tempY < 0 || tempX >= N || tempY >= N) break;
                    map[tempX][tempY] = 0;
                }
            }
        }

        // 현재 코어를 연결하지 않고 다음 코어로 진행 (탐색의 다른 경로 선택)
        dfs(idx + 1, length, connected);
    }

    // 4 방향을 나타내는 배열 (상, 하, 좌, 우)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
}
