// 삼성 2020 하반기 백준 - 상어 중학교


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_21609 {
	public static int n, m;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static class Block implements Comparable<Block>{
		int x;
		int y;
		int num;
		int mu_num;
		public Block(int x, int y, int num, int mu_num) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.mu_num = mu_num;
		}
		@Override
		public int compareTo(Block o) {
			if(this.num == o.num) {
				if(this.mu_num == o.mu_num) {
					if(this.x == o.x) {
						return -(this.y - o.y);
					}
					return -(this.x - o.x);
				}
				return -(this.mu_num - o.mu_num);
			}
			return -(this.num - o.num);
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
	
	public static ArrayList<Block> list;
	public static int[][] map;
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while(true) {
			list = new ArrayList<Block>();
			// 최대 블록 찾기
			if(!find()) break;
			// 최대 블록 제거
			Collections.sort(list);
			Block b = list.get(0);
			score += b.num * b.num;
			bfs2(b.x, b.y, map[b.x][b.y]);
			// 중력
			down();
			// 반시계 90도 회전
			rotate();
			// 중력
			down();
		}
		System.out.println(score);
	}
	
	public static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void rotate() {
		int[][] map2 = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map2[i][j] = map[j][n-1-i];
			}
		}
		map = map2;
	}
	
	public static void down() {
		for(int j=0;j<n;j++) {
			outer: for(int i=n-1;i>=1;i--) {
				if(map[i][j] != -2) continue;
				int x = i-1;
				while(x >= 0) {
					if(map[x][j] >= 0) {
						map[i][j] = map[x][j];
						map[x][j] = -2;
						break;
					} else if(map[x][j] == -1){
						continue outer;
					} else {
						x--;
					}
				}
			}
		}
	}
	
	public static boolean find() {
		visited = new boolean[n][n];
		boolean check = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] > 0) {
					int[] cnt = bfs(i, j, map[i][j]);
					if(cnt[0] >= 2) {
						check = true;
						list.add(new Block(i, j, cnt[0], cnt[1]));
					} 
				}
			}
		}
		
		return check;
	}
	
	public static void bfs2(int x, int y, int num) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visited = new boolean[n][n];
		visited[x][y] = true;
		map[x][y] = -2;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == num || map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
					map[nx][ny] = -2;
				}
			}
		}
	}
	
	public static int[] bfs(int x, int y, int num) {
		int cnt = 1;
		int mu_cnt = 0;
		Queue<Node> q = new LinkedList<Node>();
		visited[x][y] = true;
		q.add(new Node(x, y));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == num || map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
					cnt++;
					if(map[nx][ny] == 0)
						mu_cnt++;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 0)
					visited[i][j] = false;
			}
		}
		return new int[] {cnt, mu_cnt};
	}
	
	

}
