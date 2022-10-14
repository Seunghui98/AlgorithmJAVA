// 삼성 2021 상반기 오후 1번 - 백준 - 마법사 상어와 블리자드
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_21611 {
	public static int n, m;
	public static int[] dx = {0, -1, 1, 0, 0};
	public static int[] dy = {0, 0, 0, -1, 1};
	
	public static int[] r_dx = {0, 1, 0, -1};
	public static int[] r_dy = {-1, 0, 1, 0};
	
	public static int[][] map;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static Node[] node_map;
	public static int[] score;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		node_map = new Node[n*n];

		score = new int[4];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int nx = n / 2;
		int ny = n / 2;
		int num = 1;
		int same_cnt = 1;
		int count = 0;
		int dir = 0;
		outer: for(int i=1;i<=n;i++) {
			while(true) {
				if(nx == 0 && ny == 0) break outer;
				nx += r_dx[dir];
				ny += r_dy[dir];
				node_map[num++] = new Node(nx, ny);
				count++;
				if(count == i) {
					count = 0;
					dir = (dir+1) % 4;
					if(i == n) break;
					if(same_cnt == 2) {
						same_cnt = 1;
						break;
					} else {
						same_cnt++;
					}
				}
			}
			if(i == n) break;
		}
		
		for(int k=0;k<m;k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b_x = n/2;
			int b_y = n/2;
			
			// 폭파
			for(int i=1;i<=s;i++) {
				b_x = n/2 + dx[d] * i;
				b_y = n/2 + dy[d] * i;
				
				if(b_x < 0 || b_x >= n || b_y < 0 || b_y >= n) {
					continue;
				}
				map[b_x][b_y] = 0;
			}
			// 구슬 이동
			moving();
			// 구슬(4개 연속)
			while(bomb()) {
				moving();
			}
			// 그룹핑
			grouping();
			
		}
		
		System.out.println(score[1] + score[2]*2 + score[3]*3);
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
	
	public static void grouping() {
		int[][] map2 = new int[n][n];
		int idx = 1;
		int start = 1;
		int end = start + 1;
		outer:while(true) {
			if(start >= n*n-1) break;
			Node node = node_map[start];
			if(map[node.x][node.y] == 0) break;
			int cnt = 1;
			while(true) {
				Node next = node_map[end];
				if(idx >= n*n) break;
				if(map[node.x][node.y] == map[next.x][next.y]) {
					cnt++;
					end++;
				} else {
					break;
				}
			}
			
			int ball_num = map[node.x][node.y];
			Node nd = node_map[idx];
			Node nd_next = node_map[idx+1];
			idx += 2;
			map2[nd.x][nd.y] = cnt;
			map2[nd_next.x][nd_next.y] = ball_num;
			cnt = 1;
			start = end;
			end = start + 1;
			if(idx >= n*n) break;
		}
		map = map2;
	}
	
	public static boolean bomb() {
		
		int start = 1;
		int end = 2;
		boolean check = false;
		while(true) {
			if(start >= n*n-1) break;
			Node node = node_map[start];
			int cnt = 1;
			if(map[node.x][node.y] == 0) {
				break;
			}
			while(true) {
				if(end >= n*n) break;
 				Node next = node_map[end];
				if(map[node.x][node.y] == map[next.x][next.y]) {
					cnt++;
					end++;
				} else {
					break;
				}
			}
			
			if(cnt >= 4) {
				check = true;
				for(int i=start;i<end;i++) {
					Node nd = node_map[i];
					score[map[nd.x][nd.y]]++;
					map[nd.x][nd.y] = 0;
				}
			} 
			start = end;
			end = start + 1;
		}
		return check;
	}
	
	public static void moving() {
		for(int i=1;i<n*n;i++) {
			Node node = node_map[i];
			if(map[node.x][node.y] == 0) {
				for(int j=i+1;j<n*n;j++) {
					Node next = node_map[j];
					if(map[next.x][next.y] != 0) {
						map[node.x][node.y] = map[next.x][next.y];
						map[next.x][next.y] = 0;
						break;
					}
				}
			}
		}
	}

}
