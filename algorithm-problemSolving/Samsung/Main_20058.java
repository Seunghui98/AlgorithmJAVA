// BOJ - 마법사 상어와 파이어스톤(20058번)
// 구현 + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20058 {
	public static int n, nn, max_cnt;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		nn = (int) Math.pow(2, n);
		map = new int[nn][nn];
	
		visited = new boolean[nn][nn];
		for(int i=0;i<nn;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<nn;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int p=0;p<q;p++) {
			int l = Integer.parseInt(st.nextToken());
			// 구역 나눠서 90도 회전
			int a = (int)Math.pow(2, l);
			int end = nn / a;
			int[][] temp = new int[nn][nn];
			for(int i=0;i<nn;i+=a) {
				for(int j=0;j<nn;j+=a) {
					rotate(i, j, a, temp);
					
				}
			}
			map = temp;
		
			// 얼음 양 조절
			int[][] map_copy = new int[nn][nn];
			
			for(int i=0;i<nn;i++) {
				for(int j=0;j<nn;j++) {
					if(map[i][j] == 0) continue;
					int ice_cnt = 0;
					for(int d=0;d<4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(0 <= nx && nx < nn && 0 <= ny && ny < nn) {
							if(map[nx][ny] > 0) ice_cnt++;
						}
					}
					if(ice_cnt < 3) {
						map_copy[i][j] = map[i][j] -1;
					} else {
						map_copy[i][j] = map[i][j];
					}
				}
				
			}
			map = map_copy;
		}
		
		// 남은 얼음 계산, 얼음 덩어리 최대
		int ans = 0;
		max_cnt = 0;
		for(int i=0;i<nn;i++) {
			for(int j=0;j<nn;j++) {
				ans += map[i][j];
				if(!visited[i][j] && map[i][j] != 0) {
					bfs(i, j);
				}
			}
		}

		System.out.println(ans);
		System.out.println(max_cnt);
		
		
		
	}
	
	public static void rotate(int r, int c, int l, int[][] temp) {
		for(int i=0;i<l;i++) {
			for(int j=0;j<l;j++) {
				temp[r+i][c+j] = map[r+l-j-1][c+i];
			}
		}
	}
	
	public static void print() {
		for(int i=0;i<nn;i++) {
			for(int j=0;j<nn;j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void bfs(int x, int y) {
		int cnt =1;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			for(int d=0;d<4;d++) {
				int nx = arr[0] + dx[d];
				int ny = arr[1] + dy[d];
				if(nx < 0 || nx >= nn || ny < 0 || ny >= nn) continue;
				if(!visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					cnt++;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		max_cnt = Math.max(cnt, max_cnt);
	}

}
