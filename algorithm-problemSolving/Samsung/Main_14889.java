// BOJ - 스타트와 링크(14889번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14889 {
	public static int n;
	public static int[][] score;
	public static int ans;
	public static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		score = new int[n][n];
		visited = new boolean[n];
	
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(ans);
	}
	
	public static void dfs(int depth, int start) {
		if(depth == n/2) {
			int sum1 = 0;
			int sum2 = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(visited[i] && visited[j]) sum1 += score[i][j];
					if(!visited[i] && !visited[j]) sum2 += score[i][j];
				}
			}

			ans = Math.min(ans, Math.abs(sum1 - sum2));
			return;
		}
		
		for(int i=start;i<n;i++) {
			visited[i] = true;
			dfs(depth+1, i+1);
			visited[i] = false;
		}
	}

}
