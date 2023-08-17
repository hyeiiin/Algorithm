import java.io.*;
import java.util.*;

public class Solution_1873_김혜인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        for(int t = 1; t <= T; t++) {
            String[] hw = br.readLine().split(" ");
            int H = Integer.parseInt(hw[0]); // 게임 맵의 높이 
            int W = Integer.parseInt(hw[1]); // 게임 맵의 너비 
            char[][] map = new char[H][W];
            int tankX = 0, tankY = 0; // 전차의 위치
            
            // 맵 초기화
            for(int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
                for(int j = 0; j < W; j++) {
                	 // 전차의 위치를 찾아 tankX, tankY에 저장 
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        tankX = i;
                        tankY = j;
                    }
                }
            }
            
            int N = Integer.parseInt(br.readLine());
            String commands = br.readLine(); // 사용자의 입력 읽기 
            
      
            for(char command : commands.toCharArray()) {
                switch(command) {
                    case 'U': // 위쪽으로 이동 
                        map[tankX][tankY] = '^'; // 방향 전환
                        if(tankX > 0 && map[tankX - 1][tankY] == '.') { // 이동 가능 확인
                            map[tankX][tankY] = '.';
                            tankX--;
                            map[tankX][tankY] = '^';
                        }
                        break;
                    case 'D': // 아래쪽으로 이동 
                        map[tankX][tankY] = 'v';
                        if(tankX < H - 1 && map[tankX + 1][tankY] == '.') {
                            map[tankX][tankY] = '.';
                            tankX++;
                            map[tankX][tankY] = 'v';
                        }
                        break;
                    case 'L': // 왼쪽으로 이동 
                        map[tankX][tankY] = '<';
                        if(tankY > 0 && map[tankX][tankY - 1] == '.') {
                            map[tankX][tankY] = '.';
                            tankY--;
                            map[tankX][tankY] = '<';
                        }
                        break;
                    case 'R': // 오른쪽으로 이동
                        map[tankX][tankY] = '>';
                        if(tankY < W - 1 && map[tankX][tankY + 1] == '.') {
                            map[tankX][tankY] = '.';
                            tankY++;
                            map[tankX][tankY] = '>';
                        }
                        break;
                    case 'S': // 포탄 발사 
                        shoot(map, tankX, tankY, H, W);
                        break;
                }
            }
            
            // 결과 출력
            System.out.println("#" + t);
            for(char[] row : map) {
                System.out.println(new String(row));
            }
        }
    }

   
    static void shoot(char[][] map, int x, int y, int H, int W) {
        switch(map[x][y]) {
            case '^':  // 위쪽으로 발사 
                while(x > 0) {
                    x--;
                    if (map[x][y] == '*') {
                        map[x][y] = '.'; // 벽돌 파괴
                        break;
                    } else if (map[x][y] == '#') {
                        break; // 강철로 만든 벽에 부딪힘
                    }
                }
                break;
            case 'v': // 아래쪽으로 발사 
                while(x < H - 1) {
                    x++;
                    if (map[x][y] == '*') {
                        map[x][y] = '.';
                        break;
                    } else if (map[x][y] == '#') {
                        break;
                    }
                }
                break;
            case '<': // 왼쪽으로 발사 
                while(y > 0) {
                    y--;
                    if (map[x][y] == '*') {
                        map[x][y] = '.';
                        break;
                    } else if (map[x][y] == '#') {
                        break;
                    }
                }
                break;
            case '>': // 오른쪽으로 발사 
                while(y < W - 1) {
                    y++;
                    if (map[x][y] == '*') {
                        map[x][y] = '.';
                        break;
                    } else if (map[x][y] == '#') {
                        break;
                    }
                }
                break;
        }
    }
}
