// SWEA - 등산로 조정(1949번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1949 {
	public static int n, k, ans;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			visited = new boolean[n][n];
			ans = 0;
			int max_value = 0;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max_value = Math.max(max_value, map[i][j]);
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(max_value == map[i][j])
						dfs(i, j, map[i][j], 1, false);
				}
			}
			
			sb.append("#"+test_case+" ").append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int x, int y, int num, int len, boolean check) {
		ans = Math.max(ans, len);
		visited[x][y] = true;
	
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(!visited[nx][ny]) {
					if(map[nx][ny] < num) {
						dfs(nx, ny, map[nx][ny], len+1, check);
					} else if(map[nx][ny] >= num && !check){
						for(int i=1;i<=k;i++) {
							if(map[nx][ny]-i < num &&(map[nx][ny]-i >= 0)) {
								dfs(nx, ny,	map[nx][ny]-i, len+1, true);
							}
						}

					}
				}
			}
		}
		visited[x][y] = false;
	}

}

