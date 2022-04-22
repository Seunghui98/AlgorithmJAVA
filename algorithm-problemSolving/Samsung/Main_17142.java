// BOJ - 연구소3(17142번)
// DFS + BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142 {
	public static int n, m, ans;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static class Node {
		int x;
		int y;
		int dis;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
	public static ArrayList<Node> virus;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		ans = Integer.MAX_VALUE;
		virus = new ArrayList<>();

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Node(i, j));
				}

			}
		}
		
		dfs(0, 0, new int[m]);
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
	}
	
	public static void dfs(int depth, int start, int[] arr) {
		if(depth == m) {
			int time = bfs(arr);
			if(time != -1) {
				ans = Math.min(ans, time);
			}
			return;
		}
		
		for(int i=start;i<virus.size();i++) {
			arr[depth] = i;
			dfs(depth+1, i+1, arr);
		}
	}
	
	public static int bfs(int[] arr) {
		Queue<Node> q = new LinkedList<>();
		int[][] visited = new int[n][n];
		for(int a:arr) {
			Node node = virus.get(a);
			visited[node.x][node.y] = 1;
			q.add(node);
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(visited[nx][ny] == 0) {
						if(map[nx][ny] != 1) {
							visited[nx][ny] = visited[node.x][node.y] + 1;
							q.add(new Node(nx, ny));
						} 
				}
			}
			}
		}
		
		int cnt = 1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 0 && visited[i][j] == 0) {
					return -1;
				} 
				if(map[i][j] == 0) {
					cnt = Math.max(cnt, visited[i][j]);
				}
				
			}
		}
		return cnt-1;
		
		
	}

}
