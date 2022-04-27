// BOJ - 색종이 붙이기(17136번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136 {
	public static int ans_min;
	public static int[][] map;
	public static int[] papers = {5, 5, 5, 5, 5};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new int[10][10];
		
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<10;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans_min = 26;
		dfs(0, 0, 0);
		
		System.out.println(ans_min==26?-1:ans_min);
	}
	
	public static void dfs(int x, int y, int cnt) {
		if(x >= 9 && y > 9) {
			ans_min = Math.min(ans_min, cnt);
			return;
		}
		if(cnt >= ans_min)
			return;
		
		if(y > 9) {
			dfs(x+1, 0, cnt);
			return;
		}
		
		if(map[x][y] == 1) {
			for(int i=5;i>=1;i--) {
				if(papers[i-1] >= 1 && check(x, y, i)) {
					lock_unlock(x, y, i, 0);
					papers[i-1]--;
					dfs(x, y+1, cnt+1);
					lock_unlock(x, y, i, 1);
					papers[i-1]++;
					
				}
			}
		} else {
			dfs(x, y+1, cnt);
		}
		
	}
	
	public static void lock_unlock(int x, int y, int type, int num) {
		for(int i=x;i<x+type;i++) {
			for(int j=y;j<y+type;j++) {
				map[i][j] = num;
			}
		}
	}
	
	public static boolean check(int x, int y, int type) {
		for(int i=x;i<x+type;i++) {
			for(int j=y;j<y+type;j++) {
				if(i < 0 || i >= 10 || j < 0 || j >= 10) return false;
				if(map[i][j] == 0) return false; 
			}
		}
		return true;
	}

}
