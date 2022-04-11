// SWEA - [Professional] 조합(5607)
// 페르마소정리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_5607_이승희 {
	public static int MOD = 1234567891;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int r = Integer.parseInt(input[1]);
			long[] fac = new long[n+1];
			fac[0] = 1;
			
			for(int i=1;i<=n;i++) {
				fac[i] = (fac[i-1] * i) % MOD;
			}
			
			long bottom = (fac[n-r] * fac[r]) % MOD;
			bottom = power(bottom, MOD-2);
			
			sb.append("#"+test_case+" ").append((fac[n]*bottom)%MOD).append("\n");
			
			
		}
		System.out.println(sb.toString());
	}
	
	public static long power(long n, int x) {
		if(x == 0) return 1L;
		long temp = power(n, x/2);
		temp = (temp * temp) % MOD;
		if(x % 2 == 0)return temp;
		else return (temp * n ) % MOD;
	}

}
