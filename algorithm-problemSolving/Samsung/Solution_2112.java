// SWEA - 보호필름(2112번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112 {
	public static int d, w, k, ans;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[d][w];
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<w;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] map_copy = new int[d][w];
			for(int i=0;i<d;i++) {
				map_copy[i] = map[i].clone();
			}
			ans = k+1;
			dfs(0, 0, map_copy);
			sb.append("#"+test_case+" ").append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dfs(int depth, int r, int[][] map_copy) {
		if(depth > ans) {
			return;
		}
	
		
		if(check(map_copy)) {
			ans = Math.min(ans, depth);
			return;
		}
		
		if(r==d) {
			return;
		}


		dfs(depth, r+1, map_copy);
		
		for(int j=0;j<w;j++) {
			map_copy[r][j] = 1;
		}
		
		dfs(depth+1, r+1, map_copy);

		for(int j=0;j<w;j++) {
			map_copy[r][j] = 0;
		}
		
		dfs(depth+1, r+1, map_copy);
		
		for(int i=0;i<w;i++) {
			map_copy[r][i] = map[r][i];
		}
		
	}
	
	public static void print(int[][] map_copy) {
		for(int i=0;i<d;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(map_copy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean check(int[][] map_copy) {
		
		for(int j=0;j<w;j++) {
			int same = 1;
			for(int i=1;i<d;i++) {
				if(same == k) break;
				if(map_copy[i-1][j] == map_copy[i][j]) {
					same++;
				} else {
					same = 1;
				}

			}
			if(same < k) {
				return false;
			}

		}
		return true;
	}

}
