import java.io.*;
import java.util.*;

public class Main_15961_김혜인 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]); // 벨트에 놓인 접시 수
        int d = Integer.parseInt(input[1]); // 초밥 종류
        int k = Integer.parseInt(input[2]); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(input[3]); // 쿠폰 번호

        int[] sushi = new int[N]; // 회전 초밥 벨트 상태를 저장하는 배열
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // HashMap을 사용해서 초밥의 종류별로 카운트
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < k; i++) {
        	// getOrDefault 주어진 키에 매핑된 값을 가져오는 메소드 
            if (map.getOrDefault(sushi[i], 0) == 0) count++; // 새로운 종류의 초밥을 먹었다면 count 증가
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }
        
        int max = count; // 최댓값 저장하기 위한 변수
        for (int i = 0; i < N - 1; i++) {
            if (max <= count) {
                if (map.getOrDefault(c, 0) == 0) max = count + 1; // 쿠폰 사용이 가능한 경우
                else max = count;
            }

            // 회전을 통해 초밥 먹었으면 해당 초밥의 카운트 감소
            map.put(sushi[i], map.get(sushi[i]) - 1);
            if (map.get(sushi[i]) == 0) count--;

            // 다음으로 먹는 초밥 해당 초밥의 카운트 증가
            if (map.getOrDefault(sushi[(i + k) % N], 0) == 0) count++;
            map.put(sushi[(i + k) % N], map.getOrDefault(sushi[(i + k) % N], 0) + 1);
        }

        System.out.println(max); // 가짓수 최댓값 출력
    }
}
