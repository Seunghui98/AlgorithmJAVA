// BOJ - 마법사 상어와 블리자드(21611번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_21611 {
	public static int n, m;
	public static int[] score;
	public static int[][] map;
	public static Node[] node_map;
	
	public static HashMap<Integer, Node> hash;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] m_dir = {0, 3, 1, 0, 2};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		node_map = new Node[n*n];
		hash = new HashMap<>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		// hash 구성
		int x = n/2;
		int y = n/2;
		int cnt = 1;
		int dir = 0;
		int num = 1;
		int nx = x;
		int ny = y;
		outer : while(true) {
			int same_dir = 0;
			int same_cnt = 0;
			while(true) {
				if(nx == 0 && ny == 0) break outer;
				nx += dx[dir];
				ny += dy[dir];
				node_map[cnt++] = new Node(nx, ny);
				same_dir++;
				if(same_dir == num) {
					same_cnt++;
					dir = (dir+1)% 4;
					same_dir = 0;
					if(same_cnt == 2) {
						same_dir = 0;
						same_cnt = 0;
						num++;
						break;
					}
				} 
			}
		}
		score = new int[4];
		
		for(int k=0;k<m;k++) {
		
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			// 얼음파편 던지기
			int s_x = n/2;
			int s_y = n/2;
			for(int i=1;i<=s;i++) {
				int n_sx = s_x + dx[m_dir[d]] * i;
				int n_sy = s_y + dy[m_dir[d]] * i;
				map[n_sx][n_sy] = 0;
			}
			
			// 당기기
			pull();
		
			// 연속하는 4개이상 폭발 -> 폭발없을 때까지 반복
			while(bomb()) {
				pull();
			
			}
			
			// 구슬 갯수 변화
			change();
		
		}
		
		int sum = 0;
		for(int i=1;i<=3;i++) {
			sum += (score[i]*i);
		}
		System.out.println(sum);
		
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
	
	public static void change() {

		int start = 1;
		int end = start +1;
		int[][] map_copy = new int[n][n];
		int map_cnt = 1;
		while(true) {
			if(start == n*n-1) break;
			Node node = node_map[start]; 
			int num = map[node.x][node.y];
			if(num == 0) break;
			int cnt = 1;
			while(true) {
				Node next = node_map[end];
				if(end >= n*n) break;
				if(map[next.x][next.y] == num) {
					end++;
					cnt++;
					continue;
				} else {
					break;
				}
			}
			Node nd = node_map[map_cnt++];
			map_copy[nd.x][nd.y] = cnt;
			nd = node_map[map_cnt++];
			map_copy[nd.x][nd.y] = num;
			start = end;
			end = start+1;
			if(map_cnt >= n*n) {
				break;
			}
		}
	
		map = map_copy;
	
	}
	
	
	public static boolean bomb() {
		boolean bomb_chk = false;
		int start = 1;
		int end = start +1;
		
		while(true) {
			if(start == n*n-1) break;
			Node node = node_map[start];
			int num = map[node.x][node.y];
			if(num == 0) break;
			int cnt = 1;
			while(true) {
				Node next = node_map[end];
				if(end >= n*n) break;
				if(map[next.x][next.y] == num) {
					end++;
					cnt++;
					continue;
				} else {
					break;
				}
			}
			
			if(cnt >= 4) {
				bomb_chk = true;
			
				score[num] += cnt;
				for(int i=start;i<end;i++) {
					Node bomb_node = node_map[i];
					map[bomb_node.x][bomb_node.y] = 0;
				}
			}
			start = end;
			end = start+1;
		}
		
		
		
		return bomb_chk;
	}
	
	public static void pull() {
		for(int i=1;i<n*n;i++) {
			Node node = node_map[i];
			if(map[node.x][node.y] != 0) continue;
			for(int j=i+1;j<n*n;j++) {
				Node nt_node = node_map[j];
				if(map[nt_node.x][nt_node.y] != 0) {
					map[node.x][node.y] = map[nt_node.x][nt_node.y];
					map[nt_node.x][nt_node.y] = 0;
					break;
				} 
			}
		}
	}

}
