// 삼성 2021 상반기 오전 2번 - 백준 - 온풍기 안녕!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_23289 {
	public static int r, c, k;
	public static int[][] map;
	public static int[][] heat;
	public static boolean[][] wall1;
	public static boolean[][] wall2;
	public static class Node {
		int x;
		int y;
		int num;
		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	public static int[] dx = {0, 0, 0, -1, 1, -1, 1, -1, 1};
	public static int[] dy = {0, 1, -1, 0, 0, 1, 1, -1, -1};
	public static int[][] dir = {{}, {5, 1, 6}, {7, 2, 8}, {7, 3, 5}, {8, 4, 6}};
	public static int[][][][] check = {{}, {{{0, 0, 0}, {-1, 0, 1}}, {{0, 0, 1}}, {{1, 0, 0}, {1, 0, 1}}}, 
			{{{0, 0, 0}, {-1, -1, 1}}, {{0, -1, 1}}, {{1, 0, 0}, {1, -1, 1}}}, 
			{{{0, -1, 0}, {0, -1, 1}}, {{0, 0, 0}}, {{0, 0, 1}, {0, 1, 0}}}, 
			{{{0, -1, 1}, {1, -1, 0}}, {{1, 0, 0}}, {{0, 0, 1}, {1, 1, 0}}}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[r+1][c+1];
		wall1 = new boolean[r+1][c+1];
		wall2 = new boolean[r+1][c+1];
		heat = new int[r+1][c+1];
		for(int i=1;i<=r;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1;j<=c;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int w = Integer.parseInt(br.readLine());
		for(int i=0;i<w;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if(z == 0) {
				wall1[x][y] = true;
			} else {
				wall2[x][y] = true;
			}
		}
		
		int cnt = 0;
		while(true) {
			// 온풍기 바람 나오기
			wind();
			// 온도 조절
			moving();
			// 밖에 온도 -1 차감
			minus();
			cnt++;
			if(check()) break;
			if(cnt > 100) break;
		}
		//print();
		System.out.println(cnt);
	}
	
	public static void print() {
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				System.out.print(heat[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void minus() {
		for(int j=1;j<=c;j++) {
			if(heat[1][j] >= 1) heat[1][j]--;
			if(heat[r][j] >= 1) heat[r][j]--;
		}
		for(int i=2;i<r;i++) {
			if(heat[i][1] >= 1) heat[i][1]--;
			if(heat[i][c] >= 1) heat[i][c]--;
		}
	}
	
	public static void moving() {
		int[][] heat2 = new int[r+1][c+1];
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(heat[i][j] <= 0) continue;
				for(int d=1;d<=4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 1 || nx > r || ny < 1 || ny > c) continue;
					int diff = heat[i][j] - heat[nx][ny];
					if(heat[i][j] < heat[nx][ny] ) continue;
					if(d == 1) {
						if(!wall2[i][j]) {
							heat2[i][j] -= diff/4;
							heat2[nx][ny] += diff/4;
						}
					} else if(d==2) {
						if(!wall2[nx][ny]) {
							heat2[i][j] -= diff/4;
							heat2[nx][ny] += diff/4;
						}
					} else if(d==3) {
						if(!wall1[i][j]) {
							heat2[i][j] -= diff/4;
							heat2[nx][ny] += diff/4;
						}
					} else {
						if(!wall1[nx][ny]) {
							heat2[i][j] -= diff/4;
							heat2[nx][ny] += diff/4;
						}
					}
				}
			}
		}
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				heat[i][j] += heat2[i][j];
			}
		}
	}
	
	public static void wind2(int x, int y, int dd) {
		int[][] heat2 = new int[r+1][c+1];
		Queue<Node> queue = new LinkedList<Node>();
		int sx = x + dx[dd];
		int sy = y + dy[dd];
		queue.add(new Node(sx, sy, 5));
		heat2[sx][sy] = 5;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.num == 1) continue;
			for(int p=0;p<3;p++) {
				int d = dir[dd][p];
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 1 || nx > r || ny < 1 || ny > c) continue;
				if(heat2[nx][ny] != 0) continue;
				int[][] check_wall = check[dd][p];
				boolean isCheck = false;
				for(int[] cc:check_wall) {
					int c_nx = node.x + cc[0];
					int c_ny = node.y + cc[1];
					if(c_nx >= 1 && c_ny >= 1 && c_nx <= r && c_ny <= c) {
						if(cc[2] == 0) {
							if(wall1[c_nx][c_ny]) {
								isCheck = true;
								break;
							}
						} else {
							if(wall2[c_nx][c_ny]) {
								isCheck = true;
								break;
							}
						}
					} 
				}
				if(!isCheck) {
					heat2[nx][ny] += node.num-1;
					queue.add(new Node(nx, ny, node.num-1));
				}
				
			}
		}
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				heat[i][j] += heat2[i][j];
			}
		}
	}
	
	
	public static void wind() {
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(map[i][j] >= 1 && map[i][j] <= 4) {
					wind2(i, j, map[i][j]);
				} 
			}
		}
	}
	
	public static boolean check() {
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(map[i][j] == 5) {
					if(heat[i][j] < k) return false;
				}
			}
		}
		return true;
	}

}
