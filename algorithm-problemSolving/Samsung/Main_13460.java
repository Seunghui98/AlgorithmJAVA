// BOJ - 구슬 탈출 2 (13460번)
// 구현 + BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main_13460 {
	public static int n, m;
	public static char[][] map;
	
	
	// 위, 아래, 왼, 오
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static boolean[][][][] visited;
	public static class Data {
		int r_x;
		int r_y;
		int b_x;
		int b_y;
		int cnt;
		public Data(int r_x, int r_y, int b_x, int b_y, int cnt) {
			this.r_x = r_x;
			this.r_y = r_y;
			this.b_x = b_x;
			this.b_y = b_y;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] data = br.readLine().split(" ");
		n = Integer.parseInt(data[0]);
		m = Integer.parseInt(data[1]);
		
		map = new char[n][m];
		int b_x = 0, b_y = 0, r_x = 0, r_y = 0;
		for(int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<m;j++) {
				if(map[i][j] == 'B') {
					b_x = i;
					b_y = j;
				} else if(map[i][j] == 'R') {
					r_x = i;
					r_y = j;
				}
			}
		}
		
		int ans = bfs(b_x, b_y, r_x, r_y);
		
		System.out.println(ans);
		

	}
	
	public static int bfs(int b_x, int b_y, int r_x, int r_y) {
		visited = new boolean[n][m][n][m];
		visited[r_x][r_y][b_x][b_y] = true;
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(r_x, r_y, b_x, b_y, 0));
		
		
		while (!q.isEmpty()) {
			Data data = q.poll();
			if(data.cnt >= 10) {
				return -1;
			}
			
			for(int d=0;d<4;d++) {
				int[] moving = move(data.r_x, data.r_y, data.b_x, data.b_y, d);
				int rx = moving[0];
				int ry = moving[1];
				int bx = moving[2];
				int by = moving[3];
				int rcnt = moving[4];
				int bcnt = moving[5];

				if(map[bx][by] != 'O') {
					if(map[rx][ry] == 'O') {
						return data.cnt+1;
					} 
					
					if(rx == bx && ry == by) {
						if(rcnt > bcnt) {
							rx -= dx[d];
							ry -= dy[d];
						} else {
							bx -= dx[d];
							by -= dy[d];
						}
					}
					if(!visited[rx][ry][bx][by]) {
						q.offer(new Data(rx, ry, bx, by, data.cnt+1));
						visited[rx][ry][bx][by] = true;
					}
				} 
			}
			
		}
		
		
		
		return -1;
	}
	
	public static int[] move(int r_x, int r_y, int b_x, int b_y, int d) {
		int r_cnt = 0;
		
		while (true) {			
			r_x += dx[d];
			r_y += dy[d];
			if(map[r_x][r_y] == '#') {
				r_x -= dx[d];
				r_y -= dy[d];
				break;
				
			}
			r_cnt++;
			if(map[r_x][r_y] == 'O') {
				break;
			}
		}
		
		int b_cnt = 0;
		while(true) {
			b_x += dx[d];
			b_y += dy[d];
			if(map[b_x][b_y] == '#') {
				b_x -= dx[d];
				b_y -= dy[d];
				break;
				
			} 
			b_cnt++;
			if(map[b_x][b_y] == 'O') {
				break;
			}
		}
		
		return new int[]{r_x, r_y, b_x, b_y, r_cnt, b_cnt};
	}
	
	 

}
