// BOJ - 주사위 굴리기2(23288번)
// 구현 + 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288 {
	public static int n, m, k;
	public static int[][] map;
	// 동 -> 남 -> 서 -> 북
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	// 윗면 - 바닥면 - 왼쪽면 - 오른쪽면 - 앞면 - 뒷면
	public static int[] dice = {1, 6, 4, 3, 5, 2};
	
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int dir = 0;
		int x = 0;
		int y = 0;
		int score = 0;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int g=0;g<k;g++) {
			x += dx[dir];
			y += dy[dir];
			if(x < 0 || x >= n || y < 0 || y >= m) {
				x -= dx[dir];
				y -= dy[dir];
				dir = (dir+2) % 4;
				x += dx[dir];
				y += dy[dir];
			}
			
			if(dir ==  0) {
				move_right();
			} else if(dir == 1) {
				move_front();
			} else if(dir == 2) {
				move_left();
			} else if(dir == 3){
				move_back();
			}
			
			int cnt = bfs(x, y, map[x][y]);
		
			score += (cnt*map[x][y]);
			
			if(dice[1] > map[x][y]) {
				dir = (dir+1)%4;
			} else if(dice[1] < map[x][y]) {
				dir = (dir==0?3:dir-1);
			}
		}
		System.out.println(score);
		
		
	}
 	
 	public static int bfs(int x, int y, int num) {
 		boolean[][] visited = new boolean[n][m];
 		Queue<int[]> q = new LinkedList<>();
 		q.add(new int[] {x, y});
 		visited[x][y] = true;
 		int cnt = 1;
 		while(!q.isEmpty()) {
 			int[] arr = q.poll();
 			for(int d=0;d<4;d++) {
 				int nx = arr[0] + dx[d];
 				int ny = arr[1] + dy[d];
 				if(0 <= nx && nx < n && 0 <= ny && ny < m) {
 					if(!visited[nx][ny] && map[nx][ny] == num) {
 						q.add(new int[] {nx, ny});
 						visited[nx][ny] = true;
 						cnt++;
 					}
 				}
 			}
 		}
 		return cnt;
 	}
 	
 	
 	public static void move_back() {
 		// 윗 - 뒷 - 바 - 앞 - 윗
 		int temp = dice[0];
 		dice[0] = dice[4];
 		dice[4] = dice[1];
 		dice[1] = dice[5];
 		dice[5] = temp;
 	}
 	
 	public static void move_front() {
 		// 윗 - 앞 - 바 - 뒷 - 윗
 		int temp = dice[0];
 		dice[0] = dice[5];
 		dice[5] = dice[1];
 		dice[1] = dice[4];
 		dice[4] = temp;
 	}
 	
 	public static void move_left() {
 		// 윗 - 왼 - 바 - 오 - 윗
 		int temp = dice[0];
 		dice[0] = dice[3];
 		dice[3] = dice[1];
 		dice[1] = dice[2];
 		dice[2] = temp;
 	}
 	
 	public static void move_right() {
 		// 윗 - 오 - 바 - 왼 - 윗
 		int temp = dice[0];
 		dice[0] = dice[2];
 		dice[2] = dice[1];
 		dice[1] = dice[3];
 		dice[3] = temp;
 	}
 	
 	

}
