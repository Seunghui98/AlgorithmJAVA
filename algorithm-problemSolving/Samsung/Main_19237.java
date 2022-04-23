// BOJ - 어른 상어(19237번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19237 {
	public static int n, m, k;
	public static int[][] map;
	public static class Node {
		int x;
		int y;
		int dir;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static class Smell {
		int num;
		int time;
		public Smell(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
	public static Node[] shark;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int[][][] shark_dir;
	public static Smell[][] smell;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		smell = new Smell[n][n];
		shark = new Node[m+1];
		shark_dir = new int[m+1][4][4];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					shark[map[i][j]] = new Node(i, j);
					smell[i][j] = new Smell(map[i][j], k);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1;i<=m;i++) {
			shark[i].dir = Integer.parseInt(st.nextToken())-1;
		}
		
		// 상어들의 방향 우선순위 받기
		for(int s=1;s<=m;s++) {
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<4;j++) {
					shark_dir[s][i][j] = Integer.parseInt(st.nextToken()) -1;
				}
			}
		}
	
		int time = 0;
		while(time <= 1000) {
			if(check()) {
				break;
			}
			time++;
			// 상어의 이동 시작
			for(int s=1;s<=m;s++) {
				
				if(shark[s] == null) continue;
				Node node = shark[s];
				int d = node.dir;
				boolean check = false;
				// 냄새 없는 방향 잡기
				for(int i=0;i<4;i++) {
					int dir = shark_dir[s][d][i];
					int nx = node.x + dx[dir];
					int ny = node.y + dy[dir];
					if(0 <= nx && nx < n && 0 <= ny && ny < n) {
						if((smell[nx][ny] == null) || (smell[nx][ny].time < time)) {
							// 이동
							d = dir;
							check = true;
							break;
						}
					}
				}
				// 방향 못 찾음 -> 자기 냄새 있는 곳 찾기
				if(!check) {
					for(int i=0;i<4;i++) {
						int dir = shark_dir[s][d][i];
						int nx = node.x + dx[dir];
						int ny = node.y + dy[dir];
						if(0 <= nx && nx < n && 0 <= ny && ny < n) {
							if(smell[nx][ny].num == s) {
								// 이동
								d = dir;
								break;
							}
						}
					}
				}
				
				
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				node.dir = d;
			
				if(map[nx][ny] == 0) {
					map[nx][ny] = s;
					map[node.x][node.y] = 0;
					shark[s].x = nx;
					shark[s].y = ny;
				} else if(map[nx][ny] > s){
					int delete = map[nx][ny];
					shark[delete] = null;
					map[nx][ny] = s;
					map[node.x][node.y] = 0;
					shark[s].x = nx;
					shark[s].y = ny;
				} else {
					shark[s] = null;
					map[node.x][node.y] = 0;
				}
				
				
				
			
			}
			
			// 냄새 뿌리기
			for(int s=1;s<=m;s++) {
				if(shark[s] != null) {
					smell[shark[s].x][shark[s].y] = new Smell(s, k+time);
				}
			}
		}
		
		System.out.println(time>1000?-1:time);
	}
	public static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static boolean check() {
		for(int i=2;i<=m;i++) {
			if(shark[i] != null) {
				return false;
			}
		}
		return true;
	}

}
