// BOJ - 파이프 옮기기1 (17070번)
// BFS
import java.awt.image.CropImageFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070 {
	public static int n;
	public static int[][] map;
	public static boolean[][][] visited;
	public static int ans;
	
	public static int[] dx = {0, 1, 1};
	public static int[] dy = {1, 0, 1};

	public static class Node {
		int x;
		int y;
		int dir;
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		if(map[n-1][n-1] != 1) {
			bfs(0, 1, 0);
		}
		
	
		System.out.println(ans);
		
	}
	
	public static void bfs(int x, int y, int dir) {
		Queue<Node> q = new LinkedList<Node>();

		q.offer(new Node(x, y, dir));
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			if(node.x == n-1 && node.y == n-1) {
				ans++;
			}
			for(int d=0;d<3;d++) {
				if(node.dir == 0 && d == 1) continue;
				if(node.dir == 1 && d == 0) continue;
				
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					

						if(map[nx][ny] == 1) continue;
						if(d==2) {
							if(!cross_possible(node.x, node.y)) continue;
						}
				
					
						q.offer(new Node(nx, ny, d));
					
				
				}
			}
			
		}
	}
	
	public static boolean cross_possible(int x, int y) {
		for(int d=0;d<3;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(map[nx][ny] == 1) return false;
			}
		}
		return true;
	}

}
