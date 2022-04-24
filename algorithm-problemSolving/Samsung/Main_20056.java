// BOJ - 마법사 상어와 파이어볼 (20056번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20056 {
	public static int n, m, k;

	public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	public static ArrayList<Node>[][] map;
	
	public static class Node {
		int x;
		int y;
		int m;
		int d;
		int s;
		public Node(int x, int y, int m, int d, int s) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new ArrayList[n][n];
		
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(map[x-1][y-1] == null) {
				map[x-1][y-1] = new ArrayList<Node>();
				map[x-1][y-1].add(new Node(x-1, y-1, m, d, s));
			} else {
				map[x-1][y-1].add(new Node(x-1, y-1, m, d, s));
			}
		}
		
		while(k-- > 0) {
			// 파이어볼 이동
			ArrayList<Node>[][] map_copy = new ArrayList[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] == null) continue;
					for(int p=0;p<map[i][j].size();p++) {
						Node node = map[i][j].get(p);

						int nx = node.x + dx[node.d] * (node.s % n);
						int ny = node.y + dy[node.d] * (node.s % n);
		
						if(nx < 0) {
							nx = n - Math.abs(nx);
						} else if(nx >= n) {
							nx -= n;
						}
						if(ny < 0) {
							ny = n - Math.abs(ny);
						} else if(ny >= n) {
							ny -= n;
						}
			
						
						if(map_copy[nx][ny] == null) {
							map_copy[nx][ny] = new ArrayList<>();
						} 
						map_copy[nx][ny].add(new Node(nx, ny, node.m, node.d, node.s));
					}
				}
			}
			
			// 중복 처리
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map_copy[i][j] == null || map_copy[i][j].size() == 1) {
						continue;
						
					}
					int sum_m = 0;
					int sum_s = 0;
					int size = map_copy[i][j].size();
					boolean check1 = true; // 홀수 체크
					boolean check2 = true; // 짝수 체크
					for(int p=0;p<map_copy[i][j].size();p++) {
						Node nd = map_copy[i][j].get(p);
						sum_m += nd.m;
						sum_s += nd.s;
						if(nd.d % 2 == 1) {
							check2 = false;
						} else {
							check1 = false;
						}
					}
					map_copy[i][j].clear();
					if(sum_m / 5 != 0) {

						for(int p=0;p<4;p++) {
							int dir = 0;
							if(check1 || check2) {
								dir = p*2;
							} else {
								dir = p*2+1;
							}
							map_copy[i][j].add(new Node(i, j, sum_m/5, dir, sum_s/size));
						}
					} 
				}
			}
			map = map_copy;
		}
		
		int ans = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == null) continue;
				for(int p=0;p<map[i][j].size();p++) {
					ans += map[i][j].get(p).m;
				}
			}
		}
		System.out.println(ans);
	}

}
