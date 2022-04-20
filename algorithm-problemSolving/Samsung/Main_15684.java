// BOJ - 사다리 조작 (15684번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15684 {
	
	public static int n, m, ans;	
	public static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static ArrayList<Node> list; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			boolean[][] map = new boolean[n+2][m+2];
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = true;
			}
		

		
			ans = 4;
			list = new ArrayList<>();

			for(int i=1;i<=n;i++) {
				for(int j=1;j<=m;j++) {
					if(!map[i][j]) {
						list.add(new Node(i, j));
					}
				}
			}
			
			boolean[][] map_copy = new boolean[n+1][m+1];
			
			for(int p=1;p<=n;p++) {
				map_copy[p] = map[p].clone();
			}
			
			dfs(0, 0, map_copy);
			
			if(ans == 4) {
				System.out.println(-1);
			} else {
				System.out.println(ans);
			}
		
		
	}
	
	public static void dfs(int depth, int start, boolean[][] map_copy) {
		if(depth >= ans) return;
	
		if(check1(map_copy)) {
			ans = depth;
			return;
		}

		for(int i=start;i<list.size();i++) {
			Node node= list.get(i);
			if(map_copy[node.x][node.y-1] || map_copy[node.x][node.y+1]) continue;
			map_copy[node.x][node.y] = true;
			dfs(depth+1, i+1, map_copy);
			map_copy[node.x][node.y] = false;
		}
	}
	
	public static boolean check1(boolean[][] map_copy) {
		for(int j=1;j<=m;j++) {
			int now = j;
			
			for(int i=1;i<=n;i++) {
				if(map_copy[i][now-1]) {
					now -= 1; 
				} else if(map_copy[i][now]) {
					now += 1;
				}
			}
			
			if(now != j) {
				return false;
			}
		}
		

		return true;
	}

}
