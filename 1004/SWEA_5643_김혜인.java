import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Solution {

    
    static int t, n, m; // 테스트 케이스 수, 학생 수, 관계 수
    static ArrayList<Integer>[] adjList; // 각 학생별로 더 큰 학생들의 정보를 저장하는 인접 리스트
    static int[] biggerCount; // 각 학생보다 큰 학생의 수를 저장하는 배열
    static boolean[] visited; 
    static int tempCount; // 현재 DFS 탐색에서 만난 학생 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine()); // 테스트 케이스 수를 입력 받음

        for (int tc = 1; tc <= t; tc++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]); // 학생 수 
            m = Integer.parseInt(input[1]); // 관계 수 

            adjList = new ArrayList[n + 1];
            biggerCount = new int[n + 1];

            // 인접 리스트 초기화
            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 학생 간의 관계를 입력 받아 인접 리스트에 저장
            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                adjList[a].add(b); // a보다 b가 크다는 정보를 인접 리스트에 추가
            }

            for (int i = 1; i <= n; i++) {
                tempCount = 0;
                visited = new boolean[n + 1]; // 방문 배열 초기화
                dfs(i);
                biggerCount[i] += tempCount; // 탐색된 결과 저장
            }

            // biggerCount 배열에서 학생 수와 동일한 값을 가진 항목 찾기
            int totalCount = 0;
            for (int count : biggerCount) {
                if (count == n) totalCount++;
            }
            
            System.out.print("#" + tc + " " + totalCount + "\n");
        }
    }

    private static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start); // 시작 학생 스택에 추가

        // 스택이 빌 때까지 탐색
        while (!stack.isEmpty()) {
            int current = stack.pop(); // 현재 탐색 중인 학생
            
            if (visited[current]) continue; // 이미 방문한 학생이면 건너뛰기
            
            visited[current] = true; // 학생 방문 체크
            tempCount++; // 탐색된 학생 수 증가
            
            // 현재 학생과 관계 있는 학생들 탐색
            for (int next : adjList[current]) {
                if (!visited[next]) {
                    biggerCount[next]++; // 큰 학생 카운트 증가
                    stack.push(next); // 아직 방문하지 않은 학생 스택에 추가
                }
            }
        }
    }
}
