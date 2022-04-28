// BOJ - 파이프 옮기기1(17070)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_3 {
	public static int n, ans ;
	public static int[][] map;
	//가로, 세로, 대각
	public static int[] dx = {0, 1, 1};
	public static int[] dy = {1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		dfs(0, 1, 0);
		System.out.println(ans);

	}
	
	public static void dfs(int x, int y, int dir) {
		if(x == n-1 &&  y == n-1) {
			ans++;
			return;
		}
		
		if(dir == 0) {
			if(y+1<n && map[x][y+1] != 1) {
				dfs(x, y+1, 0);
			}
		} else if(dir == 1) {
			if(x+1<n && map[x+1][y] != 1) {
				dfs(x+1, y, 1);
			}
		} else if(dir ==2) {
			if(y+1<n && map[x][y+1] != 1) {
				dfs(x, y+1, 0);
			}
			if(x+1<n && map[x+1][y] != 1) {
				dfs(x+1, y, 1);
			}
		}
		
		if(y+1 < n && x+1 < n && map[x][y+1] != 1 && map[x+1][y] != 1 && map[x+1][y+1] != 1) {
			dfs(x+1, y+1, 2);
		}
		
		
	}
	
	

}
