// BOJ - 원판 돌리기(17822번)
// BFS + 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17822 {
	public static int n, m, t;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int p=0;p<t;p++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
		
			rotate(x, d, k);
			
			int cnt = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j] != 0) {
						cnt += cal(i, j, map[i][j]);
					}
				}
			}
			
			if(cnt == 0) {
				int[] arr = sum();
				double avg = (double) arr[0] / (n*m - arr[1]);
				
				for(int i=0;i<n;i++) {
					for(int j=0;j<m;j++) {
						if(map[i][j] != 0) {
							if(map[i][j] > avg) {
								map[i][j] -= 1;
							} else if(map[i][j] < avg) {
								map[i][j] += 1;
							}
						}
					}
				}
				
			}
			

		}
		
		int ans = sum()[0];
		System.out.println(ans);
	}
	
	public static int[] sum() {
		int[] re  = new int[2];
		int sum = 0;
		int zero_cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				sum += map[i][j];
				if(map[i][j] == 0) zero_cnt++;
			}
		}
		re[0] = sum;
		re[1] = zero_cnt;
		return re;
	}
	
	public static int cal(int x, int y, int num) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		int cnt = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 0 || nx >= n) {
					continue;
				}
				
				if(ny < 0 || ny >= m) {
					ny = (ny==-1?m-1:0);
				}
				
				if(map[nx][ny] != 0 && map[nx][ny] == num) {
					cnt++;
					map[nx][ny] = 0;
					q.add(new Node(nx, ny));
				}
			}
		}
		
		if(cnt == 0) {
			map[x][y] = num;
		}
		return cnt;
	}
	
	public static void rotate(int x, int d, int k) {
		if(d== 0) {
			for(int i=1;i<=n;i++) {
				if(i%x == 0) {
					int[] temp = new int[m];
					
					for(int j=0;j<m;j++) {
						temp[(j + k) % m] = map[i-1][j];
					}
					
					for(int j=0;j<m;j++) {
						map[i-1][j] = temp[j];
					}
					
				}
			}
		} else {
			for(int i=1;i<=n;i++) {
				if(i%x == 0) {
					int[] temp = new int[m];
					
					for(int j=0;j<m;j++) {
						int idx = (j-k < 0)?(m + (j-k)):(j-k);
						temp[idx] = map[i-1][j];
					}
					
					for(int j=0;j<m;j++) {
						map[i-1][j] = temp[j];
					}
					
				}
			}
		}
	}
	

}
