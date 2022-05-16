// BOJ - 대기업 승범이네(17831번)
// Tree DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17831 {
	public static int n;
	public static int[] power;
	public static int[][] dp;
	public static int ans;
	public static int[] checked;
	public static ArrayList<ArrayList<Integer>> connect;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		power = new int[n+1];
		dp = new int[n+1][2];
		checked = new int[n+1];
		connect = new ArrayList<ArrayList<Integer>>();
		connect.add(new ArrayList<>());
		connect.add(new ArrayList<>());
		for(int i=0;i<=n;i++) {
			connect.add(new ArrayList<>());
		}
		for(int i=2;i<=n;i++) {
			int num = Integer.parseInt(st.nextToken());
			connect.get(num).add(i);
		}
	
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1;i<=n;i++) {
			power[i] = Integer.parseInt(st.nextToken()); 
			dp[i] = new int[] {-1, -1};

		}

		ans = 0;
		ans = Math.max(dfs(1,0), dfs(1,1));
		
		System.out.println(ans);
	}
	
	public static int dfs(int x, int stat) {
		int max_sum = dp[x][stat];
	
		if(max_sum != -1) {
			return max_sum;
		}
		max_sum = 0;
	
		// 현재 노드가 멘티 or 아무것도 x
		if(stat == 0) {
			int sum = 0;
			for(int i:connect.get(x)) {
				sum += Math.max(dfs(i, 0), dfs(i, 1));
			}
			max_sum = Math.max(max_sum, sum);
		} else {
			// 현재 노드가 멘토 경우 -> 연결된 노드 중 하나의 멘티가 존재해야함
			int temp = 0;
			for(int i:connect.get(x)) {
				int temp1 = dfs(i, 0);
				int temp2 = dfs(i, 1);
				if(temp1 > temp2) {
					temp += temp1;
					checked[i] = 0;
				} else {
					temp += temp2;
					checked[i] = 1;
				}
			}
			
			for(int i:connect.get(x)) {
				int sum = temp - dfs(i, checked[i]);
				sum += (dfs(i, 0) + power[i]*power[x]);
				max_sum = Math.max(max_sum, sum);
			}
			
			
			
		}
		dp[x][stat] = max_sum;
		
		return max_sum;
	}

}
