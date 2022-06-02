// BOJ - 인내의 도미노 장인 호석(20165번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20165 {
	public static int n, m, r, score;
	// 동, 서, 남, 북
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	public static boolean[][] visited;
	public static int[][] map;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1;j<=m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			if(!visited[x][y])
				bfs(x, y, dir);
			
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			visited[x][y] = false;
			
		}
		
		System.out.println(score);
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if(visited[i][j]) {
					System.out.print("F ");
				} else {
					System.out.print("S ");		
				}
			}
			System.out.println();
		}

	}
	
	public static void bfs(int x, int y, char dir) {
		Queue<Node> q = new LinkedList<>();

		int d = 0;
		if(dir == 'E') d = 0;
		else if(dir == 'W') d = 1;
		else if(dir == 'S') d = 2;
		else d = 3;
		
		q.add(new Node(x, y, d));
		score++;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int height = map[node.x][node.y];
			for(int i=1;i<height;i++) {
				int nx = node.x + dx[node.dir]*i;
				int ny = node.y + dy[node.dir]*i;
				if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
				if(!visited[nx][ny]) {

					visited[nx][ny] = true;
					score++;
					q.add(new Node(nx, ny, node.dir));
				}
			}
			
		}
	}
	


}

