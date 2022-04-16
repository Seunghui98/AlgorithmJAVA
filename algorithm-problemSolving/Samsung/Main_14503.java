// BOJ - 로봇 청소기(14503번)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503 {
	public static int n, m;
	public static int[][] map;
	public static int x, y, dir;
	// 북 , 동, 남, 서
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		outer1:while(true) {
			visited[x][y] = true;
			cnt++;

			// 인접한 칸 탐색
			boolean check = false;
			int rotate_cnt = 0;
			// 인접한 칸 탐색
			outer2:while(true) {
				for(int i=0;i<4;i++) {
					int d = (dir==0?3:dir-1);
					int	nx = x + dx[d];
					int ny = y + dy[d];
					if(0 <= nx && nx < n && 0 <= ny && ny < m) {
						if(!visited[nx][ny] && map[nx][ny] != 1) {
							dir = (dir==0?3:dir-1);
							x = nx;
							y = ny;
							check = true;
							break outer2;
						}
					}
					// 왼쪽에 빈공간이나 청소할 공간 존재하지 않다면 왼쪽방향으로 회전
					dir = (dir==0?3:dir-1);
				}
				
				
				// 네 번 방향 청소할 곳 못 찾았을 때
				if(!check) {
					int d = (dir<2?dir+2:dir-2);
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(nx < 0 || nx >=n || ny < 0 || ny >= m || map[nx][ny] == 1) {
						break outer1;
					}
					// 후진
					x = nx;
					y = ny;
				}
				
			}
			
			
			
		}
		

		System.out.println(cnt);
	

		
		
	}
}
