import java.util.*;
import java.io.*;

public class Main_2164_김혜인 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue1 = new LinkedList<>();

		for(int i = 1; i <= N; i++) { // 카드 큐에 추가
			queue1.add(i);
		}
		
		while(queue1.size() > 1) { // 카드 버리기 반복 
			queue1.poll();	// 맨 앞 카드 버림 
			queue1.add(queue1.poll());	// 맨 앞 카드 버리고 버려진 카드 맨 뒤에 삽입 
		}
		
		int lastCard = queue1.poll();
		System.out.println(lastCard);	// 마지막 남은 카드 출력 
	}
}
