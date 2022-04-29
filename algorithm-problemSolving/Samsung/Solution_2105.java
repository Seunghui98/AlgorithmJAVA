// SWEA - 디저트 카페(2105번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105 {
	public static int n, max, s_x, s_y;
	public static int[][] map;
	public static int[] dx = {-1, 1,  1, -1};
	public static int[] dy = {1, 1, -1, -1};
	public static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visited = new boolean[101];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 1;
			for(int i=1;i<n-1;i++) {
				for(int j=0;j<n-2;j++) {
					s_x = i;
					s_y = j;
					visited[map[i][j]] = true;
					dfs(i, j, map[i][j], 0, 1);
					visited[map[i][j]] = false;
				}
			}
			if(max == 1) max = -1;
			sb.append("#"+test_case+" ").append(max+"\n");
			
		}
		System.out.println(sb.toString());

	}
	
	public static void dfs(int x, int y, int sum, int dir, int cnt) {
	
		if(x == s_x && y == s_y && dir ==3) {
			max = Math.max(max, sum);
			return;
		}
		
		// 현재 방향으로 감
		int nx = x + dx[dir];
		int ny = y + dy[dir];
	
		if(0 <= nx && nx < n && 0 <= ny && ny < n) {
		
			if(!visited[map[nx][ny]]) {
				visited[map[nx][ny]] = true;
				dfs(nx, ny, sum+map[nx][ny], dir, cnt+1);
				visited[map[nx][ny]] = false;
			}
		
			if(nx == s_x && ny == s_y) {
		
				max = Math.max(max, cnt);
				return;
			}
		}
		
		// 방향 전환
		if(dir != 3) {
			nx = x + dx[dir+1];
			ny = y + dy[dir+1];
			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(!visited[map[nx][ny]]) {
					visited[map[nx][ny]] = true;
					dfs(nx, ny, sum+map[nx][ny], dir+1, cnt+1);
					visited[map[nx][ny]] = false;
				}
				
			}
			if(nx == s_x && ny == s_y) {
			
				max = Math.max(max, cnt);
				return;
			}
			
		} 

		
	}

}
