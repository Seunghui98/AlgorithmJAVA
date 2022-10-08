package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1 {

	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static int[] c_dx = {-1, 0, 1, 0};
	public static int[] c_dy = {0, 1, 0, -1};
	
	public static int[][] c_map;
	public static int n, m, h, k;
	public static int[][] m_map;
	public static boolean[][] tree;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		m_map = new int[n+1][n+1];
		c_map = new int[n+1][n+1];
		tree = new boolean[n+1][n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			if(dir==1){
				m_map[x][y] = 0;
			} else {
				m_map[x][y] = 2;
			}
			System.out.println("?");
		}
		
		for(int i=0;i<h;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			tree[x][y] = true;
		}
		
		int nx = n/2 +1;
		int ny = n/2 +1;
		int d = 0;
		int num = 1;
		outer: while(true) {
			int cnt = 0;
			int same_cnt = 0;
			while(true) {
				nx += c_dx[d];
				ny += c_dy[d];
				c_map[nx][ny] = d;
				cnt++;
				if(cnt == num) {
					d = (d+1) % 4;
					c_map[nx][ny] = d;
					cnt = 0;
					if(num == n) {
						break outer;
					}
					if(same_cnt == 0) {
						same_cnt++;
					} else {
						num++;
						same_cnt = 0;
						break;
					}
				} 
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(c_map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}
