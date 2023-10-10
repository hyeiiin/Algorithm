import java.io.*;
import java.util.*;

public class Solution_7465_김혜인 {
    static int[] boss;

    // a와 b를 하나의 무리로 합치는 함수
    private static void union(int a, int b) {
        boss[findBoss(b)] = findBoss(a);
    }

    // a가 속한 무리의 대장을 찾는 함수
    private static int findBoss(int a) {
        if (boss[a] == a) return a;
        return boss[a] = findBoss(boss[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 사람의 수
            int M = Integer.parseInt(st.nextToken()); // 관계의 수
            boss = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                boss[i] = i; // 초기에 각 사람은 자기 자신이 대장
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                if(st.hasMoreTokens()) {
                    int b = Integer.parseInt(st.nextToken());
                    if (findBoss(a) != findBoss(b)) // 두 사람이 다른 무리에 속해 있다면
                        union(a, b); // 두 사람을 하나의 무리로 합침
                }
            }
            boolean[] count = new boolean[N+1]; // 무리의 개수를 파악하기 위한 배열
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                boss[i] = findBoss(i);
                if(!count[boss[i]]) { // 이 무리의 대장을 아직 세지 않았다면
                    cnt++;
                    count[boss[i]] = true; // 이 무리의 대장을 센 것으로 표시
                }
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
