// BOJ - 캐슬 디펜스(17135번)
// DFS + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_17135 {
	public static int n, m, d, ans;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static class Node implements Comparable<Node>{
		int x;
		int y;
		int dis;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Node(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(Node o) {
			if(this.dis == o.dis) {
				return this.y - o.y;
			}
			return this.dis - o.dis;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 0, new int[3]);
		System.out.println(ans);
	}
	
	public static void combination(int depth, int start, int[] arr) {
		if(depth == 3) {
			int[][] map_copy = new int[n][m];
			for(int i=0;i<n;i++) {
				map_copy[i] = map[i].clone();
			}
			
			int score = 0;
			for(int i=0;i<n;i++) {
				ArrayList<Node> kill = new ArrayList<>();
				
				for(int a=0;a<3;a++) {
				
					Node monster = bfs(n, arr[a], map_copy);
				
					if(monster != null) {
						
						kill.add(monster);
					}
				}
				
				for(Node node:kill) {
			
					if(map_copy[node.x][node.y] != 0) {
						map_copy[node.x][node.y] = 0;
						score++;
					}
				}
				// 중력 한 칸씩 내려가기..
				for(int q=0;q<m;q++) {
					for(int p=n-1;p>=1;p--) {
						map_copy[p][q] = map_copy[p-1][q];
						map_copy[p-1][q] = 0;
					}
				}
			
			}
			
			ans = Math.max(ans, score);
			return;
		}
		for(int i=start;i<m;i++) {
			arr[depth] = i;
			combination(depth+1, i+1, arr);
		}
	}
	
	public static void print(int[][] map_copy) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(map_copy[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static Node bfs(int x, int y, int[][] map_copy) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		q.offer(new Node(x, y, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
	
			if(node.dis == d) {
				continue;
			}
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(map_copy[nx][ny] == 1) {
					pq.add(new Node(nx , ny, node.dis+1));
				}
				q.add(new Node(nx, ny, node.dis+1));
			}
		}
		
		Node node = pq.poll();

		return node;
	}

}
