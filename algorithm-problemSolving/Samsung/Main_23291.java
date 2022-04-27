// BOJ - 어항 정리(23291번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_23291 {
	public static int n, k;
	public static int[][] map;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) {
			map[n-1][i] = Integer.parseInt(st.nextToken());
		}
		
		int time = 0;
		while(true) {
			// 최대값, 최솟값 계산 
			int[] arr = max_min();
			if(arr[0] - arr[1] <= k) break;
			time++;
			
			// 최솟값 1증가
			for(int j=0;j<n;j++) {
				if(map[n-1][j] == arr[1]) map[n-1][j] += 1;
				
			}
			// 어항 정리 1
			build_fish1();
			
			// 물고기 조절
			move();
		
			// 한 줄
			line();
		
			// 어항 정리 2
			build_fish2();
		
			// 물고기 조절
			move();
			
			// 한 줄
			line();
		
		}
		
		System.out.println(time);
	}
	
	public static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void build_fish1() {
		int w = 1;
		int h = 1;
		int nw = 1;
		int nh = 2;
		int step = 0;
		int col = 0;
		
		while (nw * nh <= n) {
			for(int r=n-1;r>n-1-h;r--) {
				for(int c=col;c<col+w;c++) {
					int nr = (n-1-w) + (c-col);
					int nc = (col+w) + (n-1-r);
					map[nr][nc] = map[r][c];
					map[r][c] = 0;
				}
			}
			
			col += w;
			if(step % 2 ==0) {
				h++;
				nw++;
			} else {
				w++;
				nh++;
			}
			
			step++;
		}
		
		
	}
	
	public static void build_fish2() {
		int step = n/2;
		
		for(int j=0;j<step;j++) {
			map[n-2][(n-1)-j] = map[n-1][j];
			map[n-1][j] = 0;
		}
		
		int step_2 = step/2;
		
		for(int c=step;c<step+step_2;c++) {
			int row = 3;
			for(int r=n-2;r<n;r++) {
				map[n-(row++)][(n-1)-(c-step)] = map[r][c];
				map[r][c] = 0;
			}
		}
		
	}
	
	public static void line() {
		int[][] map_copy = new int[n][n];
		ArrayList<Integer> list = new ArrayList<>();
		for(int j=0;j<n;j++) {
			for(int i=n-1;i>=0;i--) {
				if(map[i][j] == 0) break;
				list.add(map[i][j]);
			}
		}
		
		int col = 0;
		for(Integer num:list) {
			map_copy[n-1][col++] = num;
		}
		map = map_copy;
	}
	
	public static void move() {
		int[][] map_copy = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 0) continue;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n ) continue;
					if(map[nx][ny] == 0) continue;
					if(map[i][j] < map[nx][ny]) continue;
					int diff = (map[i][j] - map[nx][ny])/5;
					if(diff > 0) {
						map_copy[i][j] -= diff;
						map_copy[nx][ny] += diff;
					}
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] += map_copy[i][j];
			}
		}
	}
	
	public static int[] max_min() {
		int max_value = 0;
		int min_value = Integer.MAX_VALUE;
		
		for(int j=0;j<n;j++) {
			if(map[n-1][j] == 0) continue;
			max_value = Math.max(max_value, map[n-1][j]);
			min_value = Math.min(min_value, map[n-1][j]);
		}
	
		return new int[] {max_value, min_value};
	}

}
