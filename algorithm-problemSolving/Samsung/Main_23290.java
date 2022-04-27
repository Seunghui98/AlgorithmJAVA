// BOJ - 마법사 상어와 복제
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_23290 {
	public static int m, s, max_cnt;
	public static int[] dx = {0, -1, -1, -1 ,0, 1, 1, 1};
	public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] s_dx = {0, -1, 0, 1, 0};
	public static int[] s_dy = {0, 0, -1, 0, 1};
	public static int s_x, s_y;
	public static ArrayList<Fish>[][] map;
	
	public static class Fish {
		int x;
		int y;
		int dir;
		public Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		map = new ArrayList[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[x-1][y-1].add(new Fish(x-1, y-1, d-1));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		s_x = Integer.parseInt(st.nextToken())-1;
		s_y = Integer.parseInt(st.nextToken())-1;
		int[][] smell = new int[4][4];
		
		for(int k=1;k<=s;k++) {
			// 상어의 물고기 복제
			ArrayList<Fish>[][] map_copy = new ArrayList[4][4];
			ArrayList<Fish>[][] map_copy2 = new ArrayList[4][4];
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					map_copy[i][j] = new ArrayList<>();
					map_copy2[i][j] = new ArrayList<>();
					for(int q=0;q<map[i][j].size();q++) {
						map_copy[i][j].add(new Fish(i, j, map[i][j].get(q).dir));
					}
				}
			}
			
			
			
			// 물고기 이동
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
		
					for(int q=0;q<map_copy[i][j].size();q++){
						int x = map_copy[i][j].get(q).x;
						int y = map_copy[i][j].get(q).y;
						int dir = map_copy[i][j].get(q).dir;
						int dir_copy = dir;
						boolean moving_check = false;
					
						for(int p=0;p<8;p++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							
							if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
								dir = (dir==0?7:dir-1);
								continue;
							}
							
							if(nx == s_x && ny == s_y) {
								dir = (dir==0?7:dir-1);
								continue;
							}
							
							if(smell[nx][ny] != 0 && (k-smell[nx][ny] <= 2)){
								dir = (dir==0?7:dir-1);
								continue;
							}
							
					
							map_copy2[nx][ny].add(new Fish(nx, ny, dir));
							moving_check = true;
							break;
						}
						
						if(!moving_check) {
					
							map_copy2[x][y].add(new Fish(x, y, dir));
						}
					}
				}
			}
			
			
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					map[i][j].clear();
					for(int q=0;q<map_copy2[i][j].size();q++) {
						map[i][j].add(new Fish(i, j, map_copy2[i][j].get(q).dir));
					}
				
				}
			}
		
			
	
			// 상어의 이동 정하기
			max_cnt = 0;
			ArrayList<Integer> moving = new ArrayList<Integer>();
			dfs(moving, new int[4], 0);
			Collections.sort(moving);
		
			String dir_number = String.valueOf(moving.get(0)); 
	
			int nx = s_x;
			int ny = s_y;
			// 정해진 이동 조합으로 이동, 냄새 남기기
			for(int i=0;i<3;i++) {
				int s_dir = dir_number.charAt(i) - '0';
				nx += s_dx[s_dir];
				ny += s_dy[s_dir];
				if(map[nx][ny].size() > 0) {
					map[nx][ny].clear();
					smell[nx][ny] = k;
				}
				
			}
			s_x = nx;
			s_y = ny;
		
			// 복제 하기
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
		
					for(int p=0;p<map_copy[i][j].size();p++) {
						map[i][j].add(new Fish(i, j, map_copy[i][j].get(p).dir));
					}
				}
			}
			
			
		}
		
		int ans = 0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				ans += map[i][j].size();
			}
		}
		System.out.println(ans);
		
	
	}
	
	public static void dfs(ArrayList<Integer> moving, int[] arr, int depth) {
		if(depth == 3) {
			int cnt = count_fish(s_x, s_y, arr);
			if(cnt == -1) return;
			else {
				int number = 0;
				for(int i=0;i<3;i++) {
					number += (arr[i]*Math.pow(10, 2-i));
				}
				
				if(max_cnt < cnt) {
					moving.clear();
					moving.add(number);
					max_cnt = cnt;
				} else if(max_cnt == cnt) {
					moving.add(number);
				}
			}
			return;
		}
		
		for(int i=1;i<=4;i++) {
			arr[depth] = i;
			dfs(moving, arr, depth+1);
		}
	}
	
	public static int count_fish(int x, int y, int[] arr) {
		int cnt = 0;
		int nx = x;
		int ny = y;
		boolean[][] visited = new boolean[4][4];
		
		for(int i=0;i<3;i++) {
			nx += s_dx[arr[i]];
			ny += s_dy[arr[i]];
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
				return -1;
			}
			if(!visited[nx][ny]) {
				cnt += map[nx][ny].size();
				visited[nx][ny] = true;
			}

		}
		
		
		return cnt;
	}

}
