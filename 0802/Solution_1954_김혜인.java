import java.util.*;
import java.io.*;
public class Solution_1954_김혜인 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] n = new int[t];
        for(int i=0; i<t; i++) {
            n[i] = Integer.parseInt(br.readLine());
        }
        
        for(int i=0; i<t; i++) {
            int[][] m = new int[10][10];
            int count = 1;
            int dir = 0;
            int x = 0;
            int y = 0;
            int[] dy = {1, 0, -1, 0};        //방향등, 깜빡이
            int[] dx = {0, 1, 0, -1};
            
            while(count<=n[i]*n[i]) {
                
                m[x][y] = count++;
                int nx = x + dx[dir];
                int ny = y + dy[dir];        // ny는 미래고, y는 현재, dy는 방향 현재에 방향을 더하면 미래다
                
                // 끝에 도달하기 방어, 숫자 만나면 방향 전환
                if(nx<0 || nx >=n[i] || ny<0 || ny>=n[i] || m[nx][ny] != 0) {
                    dir = (dir+1)%4;            // +1 하는 이유 -> 여태 0번 인덱스였어, +1 다음방향
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                }
                y = ny;
                x = nx;
                
            }
            System.out.printf("#%d\n",i+1);
            for(int j=0; j<n[i]; j++) {
                for(int k=0; k<n[i]; k++) {
                    System.out.printf("%d ",m[j][k]);
                }
                System.out.println();
            }
            
        }
    }
}
