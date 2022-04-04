// SWEA - 최장 증가 부분 수열(3307번)
// DP
package algor_0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=TC;tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int[] array = new int[n];
			int[] dp = new int[n];
			for(int i=0;i<n;i++) {
				dp[i] = 1;
				array[i] = Integer.parseInt(st.nextToken());
			}
			int ans = 0;
			for(int i=1;i<n;i++) {
				for(int j=0;j<i;j++) {
					if(array[j] <= array[i]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
						ans = Math.max(dp[i], ans);
					}
				}
			}

			sb.append("#"+tc+" ").append(ans+"\n");
			
		}
		System.out.println(sb.toString());

	}

}
