// BOJ - 마법사 상어와 토네이도(20057번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n, ans;
	public static int[][] map;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};
	public static int[][][] moving = {
			{{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, -1, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}},
			{{0, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {2, 7, 0, 7, 2}, {0, 10, -1, 10, 0}, {0, 0, 5, 0, 0}},
			{{0, 0, 2, 0, 0}, {0, 1, 7, 10 ,0}, {0, 0, 0, -1, 5}, {0, 1, 7, 10, 0}, {0, 0, 2, 0, 0}},
			{{0, 0, 5, 0, 0}, {0, 10, -1, 10, 0}, {2, 7, 0, 7, 2}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}}
	};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = n/2;
		int y = n/2;
	
		int dir = 0;
		int num = 1;
		int same_dir_cnt = 0;
		int same_num_cnt = 0;
		ans = 0;
		outer: while(true) {
		
			while(true) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				same_dir_cnt++;
		
				if(map[nx][ny] != 0) {
					move(nx, ny, dir);
				}
				x = nx;
				y = ny;
				if(same_dir_cnt == num) {
					if(same_num_cnt == 0) {
						same_dir_cnt = 0;
						same_num_cnt++;
					} else {
						same_dir_cnt = 0;
						same_num_cnt = 0;
						num++;
					}
					break;
				}
				if(x == 0 && y == 0) break outer;
			}
		
			dir = (dir+1) % 4;
			
		}
		
		
		
		System.out.println(ans);
	}
	
	public static void move(int x, int y, int dir) {
		int alph_x = -1;
		int alph_y = -1;
		int origin = map[x][y];
		int cnt = 0;
		
		for(int i=x-2;i<=x+2;i++) {
			for(int j=y-2;j<=y+2;j++) {
				int a = i-(x-2);
				int b = j-(y-2);
				if(moving[dir][a][b] == 0) continue;
				if(moving[dir][a][b] == -1) {
					alph_x = i;
					alph_y = j;
					continue;
				}
				
				double percent = (double) moving[dir][a][b] / 100;
				int sand = (int)(origin * (percent));
				cnt += sand;
				
				if(i < 0 || i >= n || j < 0 || j >= n) {
					ans += sand;
				} else {
					map[i][j] += sand;
				}
			}
		}
		
		if(alph_x < 0 || alph_x >= n || alph_y < 0 || alph_y >= n) {
			ans += (origin-cnt);
		} else {
			map[alph_x][alph_y] += (origin-cnt);
		}
		map[x][y] = 0;
	}

}
