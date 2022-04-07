// SWEA - 탈주범 검거
// BFS + 구현


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953 {
	public static int n, m, r, c, l;
	public static int[][] map;
	public static int ans;
	public static boolean[][] visited;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int[][] move = {{-1}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			map = new int[n][m];
			visited = new boolean[n][m];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<m;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int start_type = map[r][c];
			if(start_type == 0) {
				ans = 0;
			} else {
				ans = 1;
				visited[r][c] = true;
				bfs();
			}


			sb.append("#"+test_case+" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static class Node {
		int x;
		int y;
		int depth;
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	public static void bfs() {
		visited[r][c] = true;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c, 1));
		while(!q.isEmpty()) {
			int len = q.size();
			
			Node node = q.poll();

			
			if(node.depth == l) {
				continue;
			}
			
			for(int dir:move[map[node.x][node.y]]) {
				int nx = node.x + dx[dir];
				int ny = node.y + dy[dir];

				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
					if(!visited[nx][ny] && map[nx][ny] > 0) {
						if(check(map[nx][ny], dir)) {
							ans++;
							visited[nx][ny] = true;
							q.offer(new Node(nx, ny, node.depth+1));
						}
					}
				}
			}
		}
		
	}
	
	public static boolean check(int type, int dir) {

		for(int d:move[type]) {
			if(dir == 0) {
				if(d == 1) return true;
			} else if(dir == 1) {
				if(d == 0) return true;
			} else if(dir == 2) {
				if(d == 3) return true;
			} else {
				if(d == 2) return true;
			}

		}
		return false;
	}
	
	

}
