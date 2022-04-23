// BOJ - 모노미노도미노2 (20061번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061 {
	
	public static int[][] blue;
	public static int[][] green;
	public static int score;
	// 북, 남, 서, 동
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		blue = new int[4][10];
		green = new int[10][4];
		score = 0;
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 움직임
			moving_green(t, x, y);
			moving_blue(t, x, y);
			//print();
			// 터짐 확인 및 터지기
			bomb();
			//print();
			
			// 연한 칸 확인
			check();
			//print();
		}
		
		int cnt = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<4;j++) {
				cnt += green[i][j];
			}
		}

		
		for(int i=0;i<4;i++) {
			for(int j=0;j<10;j++) {
				cnt += blue[i][j];
			}
		}

		
		System.out.println(score);
		System.out.println(cnt);

	}
	
	public static void print() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(green[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(blue[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void bomb() {
		// 초록 보드
		for(int i=9;i>=0;i--) {
			int cnt = 0;
			for(int j=0;j<4;j++) {
				cnt += green[i][j];
			}

			
			if(cnt == 4) {
				score++;
				for(int j=0;j<4;j++) {
					int nx = i;
					while(true) {
						nx += dx[0];
						if(nx < 0) break;
						green[nx+1][j] = green[nx][j]; 
					}
					
				}
				i++;
			}
		}
			// 파란 보드
				for(int j=9;j>=0;j--) {
					int cnt = 0;
					for(int i=0;i<4;i++) {
						cnt += blue[i][j];
					}
					
					if(cnt == 4) {
						score++;
						for(int i=0;i<4;i++) {
							int ny = j;
							while(true) {
								ny += dy[2];
								if(ny < 0) break;
								blue[i][ny+1] = blue[i][ny]; 
							}
							
						}
						j++; 
					}
				}
	}
	
	public static void check() {
		// 초록 보드
		int g_cnt =0;
		
		for(int i=4;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j] != 0) {
					g_cnt++;
					break;
				}
			}
		}
		
		if(g_cnt > 0) {
			for(int i=9;i>=4;i--) {
				for(int j=0;j<4;j++) {
					green[i][j] = green[i-g_cnt][j];
				}
			}
			
			// 특별 경계 지우기
			for(int i=4;i<6;i++) {
					for(int j=0;j<4;j++) {
						green[i][j] = 0;
					}
				}
		}
		
		
		// 파란 보드
		int b_cnt =0;
		for(int j=4;j<6;j++) {
			for(int i=0;i<4;i++) {
				if(blue[i][j] != 0) {
					b_cnt++;
					break;
				}
			}
		}
		
		if(b_cnt > 0) {
			for(int j=9;j>=4;j--) {
				for(int i=0;i<4;i++) {
					blue[i][j] = blue[i][j-b_cnt];
				}
			}
			
			// 특별 경계 지우기
				for(int j=4;j<6;j++) {
						for(int i=0;i<4;i++) {
							blue[i][j] = 0;
						}
					}
			
		}
		
	
		
	}
	
	
	public static void moving_blue(int t, int x, int y) {
		// 파란 보드
				if(t==1) {
					int nx = x;
					int ny = y;
					blue[x][y] = 0;
					while(true) {
						nx += dx[3];
						ny += dy[3];
						if(ny < 0 || ny >= 10 || blue[nx][ny] != 0) {
							nx -= dx[3];
							ny -= dy[3];
							blue[nx][ny] = 1;
							break;
						}
					}
				} else if(t==2) {

					int nx = x;
					int ny = y+1;
					blue[x][y] = 0;
					blue[x][y+1] = 0;
					while(true) {
						nx += dx[3];
						ny += dy[3];
						if(ny < 0 || ny >= 10 || blue[nx][ny] != 0) {
							nx -= dx[3];
							ny -= dy[3];
							blue[nx][ny-1] = 1;
							blue[nx][ny] = 1;
							break;
						}
					}
				} else {
					int nx = x;
					int ny = y;
					blue[x][y] = 0;
					blue[x+1][y] = 0;
					while(true) {
						nx += dx[3];
						ny += dy[3];
						if(ny < 0 || ny >= 10 || blue[nx][ny] != 0 || blue[nx+1][ny] != 0) {
							nx -= dx[3];
							ny -= dy[3];
							blue[nx][ny] = 1;
							blue[nx+1][ny] = 1;
							break;
						}
					}
				}
	}
	
	public static void moving_green(int t, int x, int y) {
		// 초록 보드
		if(t==1) {
			int nx = x;
			int ny = y;
			green[x][y] = 0;
			while(true) {
				nx += dx[1];
				ny += dy[1];
				if(nx < 0 || nx >= 10 || green[nx][ny] != 0) {
					nx -= dx[1];
					ny -= dy[1];
					green[nx][ny] = 1;
					break;
				}
			}
		} else if(t==2) {
			int nx = x;
			int ny = y;
			green[x][y] = 0;
			green[x][y+1] = 0;
			while(true) {
				nx += dx[1];
				ny += dy[1];
				if(nx < 0 || nx >= 10 || green[nx][ny] != 0 || green[nx][ny+1] != 0) {
					nx -= dx[1];
					ny -= dy[1];
					green[nx][ny] = 1;
					green[nx][ny+1] = 1;
					break;
				}
			}
		} else {
			int nx = x+1;
			int ny = y;
			green[x][y] = 0;
			green[x+1][y] = 0;
			while(true) {
				nx += dx[1];
				ny += dy[1];
				if(nx < 0 || nx >= 10 || green[nx][ny] != 0) {
					nx -= dx[1];
					ny -= dy[1];
					green[nx][ny] = 1;
					green[nx-1][ny] = 1;
					break;
				}
			}
		}
		
		
	}
	
	

}
