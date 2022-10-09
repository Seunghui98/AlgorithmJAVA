// 삼성 2022 상반기 오후 1번 - 코드트리 - 나무박멸

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Solution_46 {
	public static int n, m, k, c;
	public static int[][] map;
	public static int[][] die;
	public static class Node implements Comparable<Node>{
		int x;
		int y;
		int cnt;
		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return o.cnt - this.cnt;
		}
	}
	public static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	public static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		die = new int[n][n];
		long cnt = 0;
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int p=1;p<=m;p++) {
			// 나무의 성장
			grow();
			// 나무의 번식
			moving(p);
			

			// 제초제 위치 선정
			Node node = pick();
			cnt += node.cnt;
			
			// 제초제 뿌리기
			for(int d=4;d<8;d++) {
				for(int s=0;s<=k;s++) {
					int nx = node.x + dx[d]*s;
					int ny = node.y + dy[d]*s;
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					die[nx][ny] = p;
					if(map[nx][ny] > 0) {
						if(nx == node.x && ny == node.y) continue; 
						map[nx][ny] = 0;
					} else {
						break;
					}
					
				}
			}
			map[node.x][node.y] = 0;


		}
		
		System.out.println(cnt);
	}
	
	public static Node pick() {
		ArrayList<Node> list = new ArrayList<>();
		int max = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == -1) continue;
				int count = 0;
					
				for(int d=4;d<8;d++) {
					for(int s=0;s<=k;s++){
						int nx = i + dx[d]*s;
						int ny = j + dy[d]*s;
						if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
						if(map[nx][ny] > 0) { 
							count += map[nx][ny];
						} else {
							break;
						}
						
					}
				}
				count -= map[i][j]*3;

				if(count > max) {
					max = count;
					list.clear();
					list.add(new Node(i, j, count));
				} else if(count == max) {
					list.add(new Node(i, j, count));
				}
			}
		}
		
		if(list.size() != 0) Collections.sort(list);
		return list.get(0);
	}
	
	public static void moving(int p) {
		int[][] map2 = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] <= 0) continue;
				int count = 0;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(map[nx][ny] == 0 && (die[nx][ny] == 0 || p - die[nx][ny] > c)) count++;
				}
				
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(map[nx][ny] == 0 && (die[nx][ny] == 0 || p - die[nx][ny] > c)) {
						map2[nx][ny] += (map[i][j] / count);
					}
				}
				
			}
		}

		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] += map2[i][j];
			}
		}
	}
	
	public static void grow() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] <= 0) continue;
				int count = 0;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(map[nx][ny] > 0) count++;
				}
				map[i][j] += count;
			}
		}
		
	}

}
