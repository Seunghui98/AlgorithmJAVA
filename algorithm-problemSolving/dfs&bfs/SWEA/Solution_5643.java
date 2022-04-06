// SWEA - 키 순서(5643번)
// DFS + 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5643 {
	public static int n, m;
	public static int big_cnt, small_cnt;
	public static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			graph = new int[n+1][n+1];
			for(int i=0;i<m;i++) {
				String[] input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				graph[a][b] = 1;  // a < b
			}
			int ans = 0;
			for(int i=1;i<=n;i++) {
				big_cnt = 0;
				small_cnt = 0;
				check_big(new boolean[n+1], i);
				check_small(new boolean[n+1], i);
				
				if(big_cnt + small_cnt == (n-1)) {
					ans++;
				}
			}
			
			sb.append("#"+test_case+" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb.toString());
	}
	
	public static void check_big(boolean[] visited, int idx) {
		visited[idx] = true;
		for(int i=1;i<=n;i++) {
			if(graph[idx][i] == 1 && !visited[i]) {
				check_big(visited, i);
				big_cnt++;
			}
		}
		
	}
	
	public static void check_small(boolean[] visited, int idx) {
		visited[idx] = true;
		for(int i=1;i<=n;i++) {
			if(graph[i][idx] == 1 && !visited[i]) {
				small_cnt++;
				check_small(visited, i);
			}
		}
	}

}
