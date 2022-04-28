// BOJ - 배열 돌리기4(17406번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17406 {
	public static int n, m, k, ans;
	public static boolean[] check;
	public static int[][] map;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static ArrayList<int[]> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		list = new ArrayList<int[]>();
		check = new boolean[k];
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list.add(new int[] {r-1, c-1, s});
		}
		ans = Integer.MAX_VALUE;
		permuation(0, new int[k]);
		
		System.out.println(ans);
	}

	
	public static void permuation(int depth, int[] arr) {
		if(depth == k) {
			int[][] map_copy = new int[n][m];
			for(int i=0;i<n;i++) {
				map_copy[i] = map[i].clone();
			}
			
			for(int a:arr) {
				int[] data = list.get(a);
				rotate(map_copy, data[0], data[1], data[2]);
			
			}
			int cnt = calcul(map_copy);
			ans = Math.min(ans, cnt);
			return;
		}
		for(int i=0;i<list.size();i++) {
			if(!check[i]) {
				arr[depth] = i;
				check[i] = true;
				permuation(depth+1, arr);
				arr[depth] = 0;
				check[i] = false;
			}

		}
	}
	
	public static void print(int[][] map_copy) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(map_copy[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int calcul(int[][] map_copy) {
		int min = Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			int sum = 0;
			for(int j=0;j<m;j++) {
				sum += map_copy[i][j];
			}
			min = Math.min(sum, min);
		}
		return min;
	}
	
	public static void rotate(int[][] map_copy, int r, int c, int s) {
		boolean[][] visited = new boolean[n][m];
	

		for(int p=s;p>=0;p--) {
			int x = r-p;
			int y = c-p;
			int temp = map_copy[x][y];
			
			for(int d=0;d<4;d++) {
				int nx = x;
				int ny = y;
				
				while(true) {
					nx += dx[d];
					ny += dy[d];
					if(nx < r-p || nx > r+p || ny < c-p || ny > c+p || visited[nx][ny]) {
						nx -= dx[d];
						ny -= dy[d];
						break;
					}
				
					visited[nx][ny] = true;
					map_copy[x][y] = map_copy[nx][ny];
					x = nx;
					y = ny;
				}

			}
			if(p != 0)
				map_copy[x][y+1] = temp;
		}
	}

}
