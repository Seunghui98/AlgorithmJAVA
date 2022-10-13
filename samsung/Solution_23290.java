// 삼성 2021 하반기 오후 2번 - 백준 - 어항정리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_23290 {
	public static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	public static int[] dy = {-1, -1, 0, 1, 1, 1, 0 , -1};
	
	public static int[] s_dx = {-1, 0, 1, 0};
	public static int[] s_dy = {0, -1, 0, 1};
	
	public static ArrayList<int[]> combi_dir;
	
	public static class Node implements Comparable<Node> {
		int x;
		int y;
		int dir;
		int f;
		public Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		public Node(int x, int y, int dir, int f) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.f = f;
		}

		@Override
		public int compareTo(Node o) {
			if(this.f == o.f) {
				return this.dir - this.f;
			}
			return this.f - o.f;
		}

		
	}
	
	public static ArrayList<Node>[][] map;
	public static int[][] smell;
	public static int m, s;
	public static int s_x, s_y;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		map = new ArrayList[4][4];
		smell = new int[4][4];
		combi_dir = new ArrayList<int[]>();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				map[i][j] = new ArrayList<Node>();
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			map[x-1][y-1].add(new Node(x-1, y-1, dir-1));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		s_x = Integer.parseInt(st.nextToken())-1;
		s_y = Integer.parseInt(st.nextToken())-1;
		dfs(0, new int[3]);
		
		for(int k=1;k<=s;k++) {
			ArrayList<Node> copy = new ArrayList<Node>();
			
			// 물고기 복제
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					for(int p=0;p<map[i][j].size();p++) {
						copy.add(new Node(i, j, map[i][j].get(p).dir));
					}
				}
			}
			
			// 물고기 이동
			ArrayList<Node>[][] map2 = new ArrayList[4][4];
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					map2[i][j] = new ArrayList<Node>();
				}
			}

			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					for(int p=0;p<map[i][j].size();p++) {
						boolean check = false;
						int x = map[i][j].get(p).x;
						int y = map[i][j].get(p).y;
						int dir = map[i][j].get(p).dir;
						
						for(int t=0;t<8;t++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
								dir = dir==0?7:dir-1;
								continue;
							}
							if(nx == s_x && ny == s_y) {
								dir = dir==0?7:dir-1;
								continue;
							}
							if(smell[nx][ny] != 0 && (k - smell[nx][ny] <= 2)) {
								dir = dir==0?7:dir-1;
								continue;
							}
							map2[nx][ny].add(new Node(nx, ny, dir));
							check = true;
							break;
							
						}
						
						if(!check) {
							map2[i][j].add(map[i][j].get(p));
						} 
					}
				}
			}
			
			map = map2;

			int max = 0;
			// 상어 이동지 선택
			ArrayList<String> list = new ArrayList<String>();
			for(int[] arr:combi_dir) {
				int cnt = fish(arr);
				if(cnt == -1) continue;
				if(cnt > max) {
					list.clear();
					String str = "";
					for(int a:arr) {
						str += String.valueOf(a);
					}
					list.add(str);
					max = cnt;
					
				} else if(cnt == max) {
					String str = "";
					for(int a:arr) {
						str += String.valueOf(a);
					}
					list.add(str);
				}
			}
			
			Collections.sort(list);
			// 상어 이동 + 냄새 남기기
			String dir_str = list.get(0);
			int nx = s_x;
			int ny = s_y;
			for(int i=0;i<dir_str.length();i++) {
				int dir = dir_str.charAt(i) -'0';
				dir--;
				nx += s_dx[dir];
				ny += s_dy[dir];
				if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
					nx -= s_dx[dir];
					ny -= s_dy[dir];
					continue;
				}
				if(map[nx][ny].size() > 0) {
				map[nx][ny].clear();
				smell[nx][ny] = k;
				}
			}
			s_x = nx;
			s_y = ny;
			// 복제 마법
			for(Node node:copy) {
				map[node.x][node.y].add(new Node(node.x, node.y, node.dir));
			}

			
		}
		
		int cnt = 0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				cnt += map[i][j].size();
			}
		}
		
		System.out.println(cnt);
	}
	
	public static void print() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(map[i][j].size()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int fish(int[] dir) {
		int cnt = 0;
		int nx = s_x;
		int ny = s_y;
		boolean[][] check = new boolean[4][4];
		for(int d:dir) {
			nx += s_dx[d-1];
			ny += s_dy[d-1];
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
				return -1;
			}
			if(check[nx][ny]) continue;
			check[nx][ny] = true;
			cnt += map[nx][ny].size();
		}
		return cnt;
	}
	
	public static void dfs(int depth, int[] arr) {
		if(depth == 3) {
			int[] arr_copy = arr.clone();
			combi_dir.add(arr_copy);
			return;
		}
		
		for(int i=1;i<=4;i++) {
			arr[depth] = i;
			dfs(depth+1, arr);
		}
	}

}
