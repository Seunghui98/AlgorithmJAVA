// BOJ - 상어 중학교 (21609번)
// BFS + 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main_21609 {
	public static int n, m;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static class Block implements Comparable<Block>{
		int x;
		int y;
		int size;
		int mu_cnt;
		public Block(int x, int y, int size, int mu_cnt) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.mu_cnt = mu_cnt;
		}
		@Override
		public int compareTo(Block o) {
			if(this.size == o.size) {
				if(this.mu_cnt == o.mu_cnt) {
					if(this.x == o.x) {
						return -(this.y - o.y);
					}
					return -(this.x - o.x);
				}
				return -(this.mu_cnt-o.mu_cnt);
			}
			return -(this.size - o.size);
		}
	}
	public static ArrayList<Block> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		list = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		
		while(true) {
			// 최대 길이 블록 그룹 찾기
			visited = new boolean[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!visited[i][j] && map[i][j] > 0) {
						bfs(i, j, map[i][j]);
					}
				}
			}
			
			Collections.sort(list);
			// 블록 그룹 제거
			if(list.size() == 0) {
				break;
			}
			
			
			Block b = list.get(0);
		
			bfs2(b.x, b.y);
			score += b.size * b.size;
			
			
			// 중력
			fall();
			
			// 반시계 회전
			moving();
			
			// 중력
			fall();
		
			list.clear();
		}
		
		
		

		
		System.out.println(score);
	}
	
	public static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void moving() {
		int[][] temp = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				temp[i][j] = map[j][n-i-1];
			}
		}
		map = temp;
	}
	
	public static void fall() {
		for(int j=0;j<n;j++) {
			for(int i=n-1;i>=1;i--) {
				if(map[i][j] != -2) continue;
				int nx = i;
				while(true) {
					nx -= 1;
					if(nx < 0) break;
					if(map[nx][j] == -1) break;
					if(map[nx][j] != -2) {
						map[i][j] = map[nx][j];
						map[nx][j] = -2;
						break;
					}
				}
			}
		}
	}
	
	public static void bfs2(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x, y});
		int c = map[x][y];
		map[x][y] = -2;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			for(int d=0;d<4;d++) {
				int nx = arr[0] + dx[d];
				int ny = arr[1] + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					if(map[nx][ny] == 0 || map[nx][ny] == c) {
						map[nx][ny] = -2;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
		
	}
	
	public static void bfs(int x, int y, int c) {
		boolean[][] visited2 = new boolean[n][n];
		
		visited[x][y] = true;
		visited2[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		int size = 1;
		int mu_cnt = 0;
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			for(int d=0;d<4;d++) {
				int nx = arr[0] + dx[d];
				int ny = arr[1] + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					if(map[nx][ny] == -1) continue;
					
					if(!visited2[nx][ny]) {
						if(map[nx][ny] == 0) {
							mu_cnt++;
							size++;
							visited2[nx][ny] = true;
							q.add(new int[] {nx, ny});
						} else if(map[nx][ny] == c) {
							size++;
							visited[nx][ny] = true;
							visited2[nx][ny] = true;
							q.add(new int[] {nx, ny});
							
						}
					}
				}
			}
		}
		
		if(size == 1) {
			return;
		} else {
			list.add(new Block(x, y, size, mu_cnt));
		}
		
	}

}
