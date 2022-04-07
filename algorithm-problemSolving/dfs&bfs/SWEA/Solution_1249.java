// SWEA - 보급로(1249번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Solution_1249 {
	public static int n;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static class Node {
		int x;
		int y;
		int dis;
		public Node(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++){
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			for(int i=0;i<n;i++) {
				String[] data = br.readLine().split("");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(data[j]);
				}
			}
			int ans = bfs();
			sb.append("#"+test_case).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());

	}
	
	public static int bfs() {
		int[][] visited = new int[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		Queue<Node> q = new LinkedList<Node>();
		visited[0][0] = 0;
		q.offer(new Node(0, 0, 0));
		int dis = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.x == n-1 && node.y == n-1) {
				dis = Math.min(dis, node.dis);
				continue;
			}
			
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					int distance = node.dis + map[nx][ny];
					if(visited[nx][ny] > distance) {
						visited[nx][ny] = distance;
						q.add(new Node(nx, ny, distance));
					} 
				}
			}
		}
		
		return dis;
		
	}

}
