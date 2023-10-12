
import java.io.*;
import java.util.*;

public class Main_1182_김혜인{

	static int N;
	static int S;
	static int sum;
	static int cnt;
	static int[] arr;
	// output 배열말고 리스트로 받기
	static ArrayList<Integer> output = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		// 초기화
		arr = new int[N];
		cnt = 0;

		// arr 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		powerSet(0);
//		if (S == 0) {
//			cnt -= 1; // cnt = cnt -1;
//		}
		System.out.println(cnt);

	}

	private static void powerSet(int depth) {

		// main에 초기화 하면 안됨 주의
		sum = 0;

		if (depth == N) {
			// 공집합일 때 제외시키기
			if (output.size() == 0)
				return;
			
			// output 리스트에서 i번째 원소를 가져오는 메서드 이 값을 sum에 더하여 총 합을 계산함
			// 실행 후 sum변수에는 output리스트의 모든 원소들의 합이 저장됨
			for (int i = 0; i < output.size(); i++) {
				sum += output.get(i);
			}
			
//			System.out.println(output+" "+sum); // 출력해서 확인해보기
			
			// 수열의 원소를 다 더한 값이 S가 되는 경우 cnt값 증가
			if (sum == S) {
				cnt++;
			}
			// 기저조건 return 값 필수 
			return;
		}

		output.add(arr[depth]);
		powerSet(depth + 1);
		output.remove(output.size() - 1);

		powerSet(depth + 1);

		/*
		  
      	< 정리된 버전 >
		for(int i=0; i<4; i++) {
			output.add(arr[i]);
			powerSet(depth + 1);
			output.remove(output.size() - 1);
		}

		< 펼쳐놓은 버전 >
		output.add(arr[0]);
		powerSet(depth + 1);
		output.remove(output.size() - 1);
		
		output.add(arr[1]);
		powerSet(depth + 1);
		output.remove(output.size() - 1);

		output.add(arr[2]);
		powerSet(depth + 1);
		output.remove(output.size() - 1);

		output.add(arr[3]);
		powerSet(depth + 1);
		output.remove(output.size() - 1);
		
		*/
	}

}
