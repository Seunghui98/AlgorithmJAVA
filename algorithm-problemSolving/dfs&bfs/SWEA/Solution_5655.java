// SWEA - 벽돌 깨기 (5655번)
// DFS(중복순열) + BFS

package algor_0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656 {
	public static int n, w, h;
	public static int b_cnt, ans;
	public static int[][] map;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static ArrayList<int[]> permu;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			permu = new ArrayList<int[]>();
			for(int i=0;i<h;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<w;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {
						b_cnt++;
					}
				}
			}
			
			ans = b_cnt;
			permutation(new int[n], 0);
			
			for(int[] p:permu) {
				int[][] map_copy = new int[h][w];
				for(int i=0;i<h;i++) {
					for(int j=0;j<w;j++) {
						map_copy[i][j] = map[i][j];
					}
				}
				
				for(int col:p) {
	
					int[] start = find(col, map_copy);
		
					if(start[1] != col) continue;
					bfs(start[0], start[1], map_copy);
					fall(map_copy);
					
				}
				// 남은 벽돌 갯수 세기
				count(map_copy);
			}
			sb.append("#").append(test_case).append(" ").append(ans+"\n");
		}
		System.out.println(sb.toString());

	}
	
	public static void print(int[][] map_copy) {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(map_copy[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void bfs(int x, int y, int[][] map_copy) {
		boolean[][] visited = new boolean[h][w];
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(x, y));
		visited[x][y] = true;

		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				for(int k=0;k<map_copy[node.x][node.y];k++) {
					int nx = node.x + dx[d]*k;
					int ny = node.y + dy[d]*k;
					if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
						continue;
					}
					if(visited[nx][ny]) continue;
					if(map_copy[nx][ny] != 0) {
						visited[nx][ny] = true;
						q.offer(new Node(nx, ny));
					}
				}
			}
		}
		
		int c = 0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(visited[i][j]) {
					map_copy[i][j] = 0;
					c++;
				}
			}
		}

		
	}
	
	public static void fall(int[][] map_copy) {

		for(int j=0;j<w;j++) {
			
			for(int i=h-1;i>=0;i--){
				if(map_copy[i][j] != 0) continue;
				int nx = i-1;
				while (true) {
					if(nx < 0) break;
					if(map_copy[nx][j] != 0) {
						map_copy[i][j] = map_copy[nx][j];
						map_copy[nx][j] = 0;
						break;
					}
					nx -= 1;
				}
			}
		}

	}
	
	public static int[] find(int col, int[][] map_copy) {
		int nx = 0;
		int ny = col;
		while (true) {
			if(nx >= h) {
				break;
			}
			if(map_copy[nx][ny] != 0) {
				return new int[] {nx, ny};
			}
			nx += dx[1];
			ny += dy[1];
			
		}
		return new int[] {-1, -1};
	}
	
	
	
	public static void count(int[][] map_copy) {
		int cnt = 0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map_copy[i][j] != 0) cnt++;
			}
		}
		ans = Math.min(ans, cnt);
		
	}
	
	
	
	public static void permutation(int[] arr, int depth) {
		if(depth == n) {
			int[] arr_copy = arr.clone();
			permu.add(arr_copy);
			return;
		}
		for(int i=0;i<w;i++) {

			arr[depth] = i;
			permutation(arr, depth+1);

			
		}
	}

}
