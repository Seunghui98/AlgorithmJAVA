// BOJ - 다리만들기2(17472번)
// BFS + MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_17472 {
	public static int n, m;
	public static int[][] map;
	public static int[][] sum;
	public static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int dis;
		public Edge(int a, int b, int dis) {
			this.a = a;
			this.b = b;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dis - o.dis;
		}
	}
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int[] parent;
	public static int INF = (int)1e9;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
	
		sum = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 마킹
		int cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(sum[i][j] == 0 && map[i][j] == 1) {
					bfs(i, j, ++cnt);
				}
			}
		}
		parent = new int[cnt+1];
		for(int i=1;i<=cnt;i++) {
			parent[i] = i;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
	
		
		// 섬 마다 다른 섬과 연결되는 다리(Edge) 모두 구하기
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(sum[i][j] != 0) {
					for(int d=0;d<4;d++) {
						int nx = i;
						int ny = j;
		
						int brige_cnt = 0;
						while(true) {
							nx += dx[d];
							ny += dy[d];
							if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
								break;
								
							}
							if(sum[nx][ny] == 0) {
								brige_cnt++;
								continue;
							}
							
							if(sum[nx][ny] == sum[i][j]) {
								break;
							}
							
							if(sum[nx][ny] != sum[i][j]) {

								if(brige_cnt >= 2) {
									pq.add(new Edge(sum[i][j], sum[nx][ny], brige_cnt));
									
								} 
								break;
							}
							
						}
					}
				}
			}
		}
		
		int ans = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
	
			if(find_parent(edge.a) != find_parent(edge.b)) {
				union(edge.a, edge.b);
				ans += edge.dis;
			}
		}
		
		boolean check = true;
		for(int i=1;i<=cnt;i++) {
			if(find_parent(i) != 1) {
				check = false;
				break;
			}
		}
		
		if(!check) System.out.println(-1);
		else System.out.println(ans);
		
		
	}
	
	
	
	public static void bfs(int x, int y, int cnt) {
		sum[x][y] = cnt;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		while(!q.isEmpty()){
			Node node = q.poll();
			for(int d=0;d<4;d++){
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
					if(map[nx][ny] == 1 && sum[nx][ny] == 0) {
						sum[nx][ny] = cnt;
						q.add(new Node(nx, ny));
					}
				} 
			}
		}
	}
	
	
	
	
	public static int find_parent(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find_parent(parent[x]);
	}
	
	public static void union(int a, int b) {
		a = find_parent(a);
		b = find_parent(b);
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

}
