import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_5607_김혜인 {
    static int MOD = 1234567891;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(reader.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int r = Integer.parseInt(input[1]);
            
            // 팩토리얼 값을 저장하기 위한 배열. 0!부터 n!까지의 값을 저장
            long[] factorial = new long[n + 1];
            factorial[1] = 1; // 1!은 1
            for (int i = 2; i <= n; i++) {
                factorial[i] = (factorial[i - 1] * i) % MOD; // i! = (i-1)! * i
            }
            
            // 조합 계산에서의 분모 부분. r! * (n-r)!의 값을 계산
            long denominator = (factorial[r] * factorial[n-r]) % MOD;
            
            denominator = modInverse(denominator, MOD - 2);            
            
            // 결과 출력: n! / (r! * (n-r)!) mod MOD
            System.out.println("#" + testCase + " " + (factorial[n] * denominator) % MOD );
        }
    }

    private static long modInverse(long base, int exponent) {
        if (exponent == 0) return 1; // 0승은 1
        if (exponent == 1) return base; // 1승은 자기 자신
        
        // exponent가 짝수인 경우
        if (exponent % 2 == 0) { 
            long temp = modInverse(base, exponent / 2);
            return (temp * temp) % MOD;
        }
        
        // exponent가 홀수인 경우
        long temp = modInverse(base, exponent - 1) % MOD;
        return (temp * base) % MOD;
    }
}
