// BOJ - 온풍기 안녕!
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_23289 {
	public static int r, c, k;
	public static int[][] map;
	public static int[][] heat;
	// 동, 서, 북, 남, 오위, 오아, 왼위, 왼아
	public static int[] dx = {0, 0, 0, -1 ,1, -1, 1, -1, 1};
	public static int[] dy = {0, 1, -1, 0, 0, 1, 1, -1, -1};
	
	public static int[][] check_dir = {{}, {5, 1, 6}, {7, 2, 8}, {7, 3, 5}, {8, 4, 6}};
	
	public static int[][][][] check_wall = {{}, 
			{{{0, 0, 0}, {-1, 0, 1}}, {{0, 0, 1}}, {{1, 0, 0}, {1, 0, 1}}}, 
			{{{-1, -1, 1}, {0, 0, 0}}, {{0, -1, 1}}, {{1, 0, 0}, {1, -1, 1}}}, 
			{{{0, -1, 1}, {0, -1, 0}}, {{0, 0, 0}}, {{0, 0, 1}, {0, 1, 0}}}, 
			{{{0, -1, 1}, {1, -1 ,0}}, {{1, 0, 0}}, {{0, 0, 1}, {1, 1, 0}}}};
	public static ArrayList<Node> machine;
	public static ArrayList<Integer>[][] map_wall;
	public static class Node {
		int x;
		int y;
		int dir;
		int heat;
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		public Node(int x, int y, int dir, int heat) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.heat = heat;
		}
	}

	
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		map_wall = new ArrayList[r][c];
		machine = new ArrayList<>();
		heat = new int[r][c];
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for(int j=0;j<c;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map_wall[i][j] = new ArrayList<Integer>();
				if(map[i][j] >=1 && map[i][j] <=4) {
					machine.add(new Node(i, j, map[i][j]));
				}
			}
		}
		
		int w = Integer.parseInt(br.readLine());
		for(int i=0;i<w;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			map_wall[x-1][y-1].add(t);
		}
		
		int eat = 0;
		
		while(eat <= 100) {
			for(int i=0;i<machine.size();i++) {
				Node node = machine.get(i);
				int x = node.x + dx[node.dir];
				int y = node.y + dy[node.dir];
				boolean[][] visited = new boolean[r][c];
				if(is_chk(x, y)) {
					visited[x][y] = true;
					Queue<Node> q = new LinkedList<>();
					q.add(new Node(x, y, node.dir, 5));
					while(!q.isEmpty()) {
						Node nd = q.poll();
						heat[nd.x][nd.y] += nd.heat;
					
						outer:for(int d=0;d<3;d++) {
							int dir = check_dir[nd.dir][d];
					
							int ch_x = nd.x + dx[dir];
							int ch_y = nd.y + dy[dir];
							
							if(!is_chk(ch_x, ch_y)) continue;
							if(visited[ch_x][ch_y]) continue;
							
							for(int n=0;n<check_wall[nd.dir][d].length;n++) {
								int nx = nd.x + check_wall[nd.dir][d][n][0];
								int ny = nd.y + check_wall[nd.dir][d][n][1];
					
								if(is_chk(nx, ny)) {
									for(int a=0;a<map_wall[nx][ny].size();a++) {
										if(map_wall[nx][ny].get(a) == check_wall[nd.dir][d][n][2]) {
											
											continue outer;
										}
									}

								}
								
							}
							if(nd.heat >= 2) {
							
								visited[ch_x][ch_y] = true;
								q.add(new Node(ch_x, ch_y, nd.dir, nd.heat-1));
							}
						}
					
					}
				}
				
			
			}
		
			int[][] heat_copy = new int[r][c];
			for(int i=0;i<r;i++) {
				heat_copy[i] = heat[i].clone();
			}
			
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					if(heat[i][j] == 0) continue;
					outer: for(int d=1;d<=4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(!is_chk(nx, ny)) continue;
						if(heat[i][j] < heat[nx][ny]) continue;
						int chk_x = i + check_wall[d][1][0][0];
						int chk_y = j + check_wall[d][1][0][1];
						int chk_t = check_wall[d][1][0][2];
						if(is_chk(chk_x, chk_y)) {
							for(int a=0;a<map_wall[chk_x][chk_y].size();a++) {
								if(map_wall[chk_x][chk_y].get(a) == chk_t) {
									continue outer;
								}
							}
						}
						int diff = heat[i][j] - heat[nx][ny];
						if(heat_copy[i][j]  >= diff/4) {
							heat_copy[i][j] -= diff/4;
							heat_copy[nx][ny] += diff/4;
						}
					}
				}
			}
			
			for(int i=0;i<r;i++) {
				heat[i] = heat_copy[i].clone();
			}
	
	
			// 바깥쪽 칸의 온도 1씩 감소
			for(int j=0;j<c;j++) {
				if(heat[0][j] >= 1) heat[0][j]--;
				if(heat[r-1][j] >= 1) heat[r-1][j]--; 
			}
			
			for(int i=1;i<r-1;i++) {
				if(heat[i][0] >= 1) heat[i][0]--;
				if(heat[i][c-1] >= 1) heat[i][c-1]--; 
			}
			
			eat++;

			if(check()) {
				break;
			}
			
		}
		
		System.out.println(eat>=101?101:eat);
		
		

	}
 	
 	public static void print() {
 		for(int i=0;i<r;i++) {
 			for(int j=0;j<c;j++) {
 				System.out.print(heat[i][j] +" ");
 			}
 			System.out.println();
 		}
 		System.out.println();
 	}
 	
 	public static boolean check() {
 		for(int i=0;i<r;i++) {
 			for(int j=0;j<c;j++) {
 				if(map[i][j] == 5 && (heat[i][j] < k)) return false;
 			}
 		}
 		return true;
 	}
 	
 	public static boolean is_chk(int x, int y) {
 		if(x < 0 || x >= r || y < 0 || y >= c) return false;
 		return true;
 	}

}
