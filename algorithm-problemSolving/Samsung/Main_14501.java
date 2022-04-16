// BOJ - 퇴사(14501번)
// DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14501 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n+1][2];
		for(int i=0;i<n;i++) {
			String[] input = br.readLine().split(" ");
			dp[i][0] = Integer.parseInt(input[0]);
			dp[i][1] = Integer.parseInt(input[1]);
		}
		
		for(int i=n-1;i>=0;i--) {
			if(i+dp[i][0] > n) dp[i] = dp[i+1];
			else {
				dp[i][1] = Math.max(dp[i+dp[i][0]][1]+dp[i][1], dp[i+1][1]);
			}
			
		}
		
		System.out.println(dp[0][1]);
	}

}
