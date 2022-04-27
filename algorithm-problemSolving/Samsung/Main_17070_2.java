// BOJ - 파이프 옮기기1(17070번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17070_2 {
	public static int n;
	public static int[][] map;
	//가로, 세로, 대각
	public static int[] dx = {0, 1, 1};
	public static int[] dy = {1, 0, 1};
	public static class Node{
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
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(map[n-1][n-1] != 1) {
			int cnt = bfs();
			System.out.println(cnt);
		} else {
			System.out.println(0);
		}

	}
	
	public static int bfs() {
		int cnt = 0;
		
	
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 1, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
				if(node.x == n-1 && node.y == n-1) {
					cnt++;
					continue;
				}
				for(int d=0;d<3;d++) {
					if(node.dir == 0 && d==1) continue;
					if(node.dir == 1 && d==0) continue;
					int nx = node.x + dx[d];
					int ny = node.y + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 1) continue;
					
					if(d == 2 && !check_wall(node.x, node.y, d)) continue;
					q.add(new Node(nx, ny, d));
				}
			
			
		}
		
		return cnt;
	}
	
	public static boolean check_wall(int x, int y, int dir) {
		
		for(int d=0;d<3;d++) {
			int nx = x +dx[d];
			int ny = y +dy[d];
			if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 1) return false;
		}
			
		
		return true;
	}

}
