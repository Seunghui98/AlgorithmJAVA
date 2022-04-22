// BOJ - 낚시왕(17143번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17143 {
	public static int r, c, m;
	public static Shark[][] shark;
	
	public static class Shark {
		int x;
		int y;
		int s;
		int d;
		int z;
		public Shark(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		shark = new Shark[r][c];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			shark[x-1][y-1] = new Shark(x-1, y-1, s, d-1, z);
		}
		
		int ans = 0;
		for(int j=0;j<c;j++) {
			for(int i=0;i<r;i++) {
		
				// 낚시
				if(shark[i][j] != null) {
					ans += shark[i][j].z;
					shark[i][j] = null;
					break;
				}
			}
			
			Shark[][] shark_copy = new Shark[r][c];
			
			// 상어의 이동
			for(int x=0;x<r;x++) {
				for(int y=0;y<c;y++) {
					if(shark[x][y] == null) continue;
					Shark sk = shark[x][y];
					int moving = 0;
					if(sk.d <= 1) {
						moving = (sk.s) % (r+r-2);
					} else {
						moving = (sk.s) % (c+c-2);
					}
					
					int nx = sk.x;
					int ny = sk.y;
					int dir = sk.d;
					while(moving-- > 0) {
						nx += dx[dir];
						ny += dy[dir];
						if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
							nx -= dx[dir];
							ny -= dy[dir];
							if(dir % 2 == 0) {
								dir += 1;
							} else {
								dir -= 1;
							}
							nx += dx[dir];
							ny += dy[dir];
						}
					}
					
					if(shark_copy[nx][ny] != null) {
						if(shark_copy[nx][ny].z < sk.z) {
							shark_copy[nx][ny] = new Shark(nx, ny, sk.s, dir, sk.z);
						}
					} else {
						shark_copy[nx][ny] = new Shark(nx, ny, sk.s, dir, sk.z);
					}
				}
			}
			shark = shark_copy;
			
		}
		
		System.out.println(ans);

	}
	

}
