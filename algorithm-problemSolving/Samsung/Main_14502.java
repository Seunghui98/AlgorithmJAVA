// BOJ - 연구소(14502번)
// DFS + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502 {
	public static int n, m, wall_cnt;
	public static int[][] map;
	public static int ans;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static ArrayList<Node> wall;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		wall = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					wall_cnt++;
					wall.add(new Node(i, j));
				}
			}
		}
		System.out.println(wall_cnt);
		ans = 0;
		combination(new int[3], 0, 0);
		System.out.println(ans);
	}
	
	public static void combination(int[] combi, int depth, int start) {
		if(depth == 3) {
			int[][] map_copy = new int[n][m];
			for(int i=0;i<n;i++) {
				map_copy[i] = map[i].clone();
			}
			for(int c:combi) {
				Node node = wall.get(c);
				map_copy[node.x][node.y] = 1;
		
			}
			
			boolean[][] visited = new boolean[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if (!visited[i][j] && map_copy[i][j] == 2) {
						bfs(map_copy, visited, i, j);
					}
				}
			}
			

			int cnt = check(map_copy);
	
			ans = Math.max(ans, cnt);
			return;
		}
		
		for(int i=start;i<wall_cnt;i++) {
			combi[depth] = i;
			combination(combi, depth+1, i+1);
		}
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs(int[][] map_copy, boolean[][] visited, int x, int y) {
		Queue<Node> q = new LinkedList<Node>();
		visited[x][y] = true;
		q.add(new Node(x, y));
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
					if(!visited[nx][ny] && map_copy[nx][ny] == 0) {
						map_copy[nx][ny] = 2;
						visited[nx][ny] = true;
						q.add(new Node(nx, ny));
					}
				}
			}
		}
	
		

	}
	
	public static int check(int[][] map_copy) {
		int cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map_copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
