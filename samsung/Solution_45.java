// 삼성 2022 상반기 오전 2번 - 코드트리 - 예술성
// 코드트리

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_45 {
	public static int n, g_cnt;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static ArrayList<int[]> combi;
	public static int[][] g_map;
	public static ArrayList<Node> group_list;
	
	public static class Node {
		int x;
		int y;
		int c;
		int num;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Node(int x, int y, int c, int num) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.num = num;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
	
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int sum = 0;
		for(int i=0;i<=3;i++) {
			
			if(i != 0) {
			// 십자기 회전
				rotate1();
			// 십자기 밖 회전
				rotate2();
			}
			g_map = new int[n][n];
			group_list = new ArrayList<>();
			combi = new ArrayList<>();
			g_cnt = 0;
			
			for(int p=0;p<n;p++) {
				for(int q=0;q<n;q++) {
					if(g_map[p][q] == 0) {
						g_cnt++;
						int c = grouping(p, q, g_cnt, map[p][q]);
						group_list.add(new Node(p, q, c, map[p][q]));
					}
				}
			}

			// 예술 점수 구하기
			int score = getScore();
			sum += score;
		}

		System.out.println(sum);
		
	}
	
	public static int getScore() {
		int score = 0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(g_map[i][j] == g_map[nx][ny]) continue;
					int g1 = g_map[i][j];
					int g2 = g_map[nx][ny];
					int g1_c = group_list.get(g1-1).c;
					int g2_c = group_list.get(g2-1).c;
					int g1_n = group_list.get(g1-1).num;
					int g2_n = group_list.get(g2-1).num;
					score += (g1_c+g2_c)*g1_n*g2_n;
				}
			}
		}
		return score/2;
	}
	
	public static int grouping(int x, int y, int g_num, int num) {
		int cnt = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		cnt++;
		g_map[x][y] = g_num;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i=0;i<4;i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(nx < 0 || nx >=n || ny < 0 || ny >= n) continue;
				if(g_map[nx][ny] != 0 || map[nx][ny] != num) continue;
				g_map[nx][ny] = g_num;
				cnt++;
				q.add(new Node(nx, ny));
			}
		}
		return cnt;
	}
	
	public static void rotate1() {
		int[][] map2 = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map2[i][j] = map[i][j];
			}
		}
		
		// 십자 모양 (세로->가로)
		for(int i=0;i<n;i++) {
			int j = n/2;
			map[j][i] = map2[i][j];
		}
		
		// 십자 모양 (가로->세로)
		for(int j=0;j<n;j++) {
			int i = n/2;
			map[n-j-1][i] = map2[i][j];
		}
	}
	
	public static void rotate2() {
		int[][] map2 = new int[n][n];
		int nn = n/2;
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<n/2;j++) {
				map2[i][j] = map[n-1-j-nn-1][i];
			}
		}
		
		for(int i=0;i<n/2;i++) {
			for(int j=n/2+1;j<n;j++) {
				map2[i][j] = map[n-1-j][i+nn+1];
			}
		}
		
		for(int i=n/2+1;i<n;i++) {
			for(int j=0;j<n/2;j++) {
				map2[i][j] = map[n-1-j][i-nn-1];
			}
		}
		
		for(int i=n/2+1;i<n;i++) {
			for(int j=n/2+1;j<n;j++) {
				map2[i][j] = map[n-1-j+nn+1][i];
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i == n/2 || j == n/2) continue;
				map[i][j] = map2[i][j];
			}
		}
		
	}
	


}
