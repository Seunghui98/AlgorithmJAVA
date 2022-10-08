import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_44 {


	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static int[] c_dx = {-1, 0, 1, 0};
	public static int[] c_dy = {0, 1, 0, -1};
	
	public static int[][] c_map1;
	public static int[][] c_map2;
	public static int n, m, h, k;
	public static ArrayList<Integer>[][] m_map;
	public static boolean[][] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		boolean turn = false;
		m_map = new ArrayList[n+1][n+1];
		c_map1 = new int[n+1][n+1];
		c_map2 = new int[n+1][n+1];
		tree = new boolean[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				m_map[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			if(dir==1){
				m_map[x][y].add(0);
			} else {
				m_map[x][y].add(2);
			}
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
				c_map1[nx][ny] = d;
				c_map2[nx][ny] = (d+2)%4;
				cnt++;
				if(cnt == num) {
					d = (d+1) % 4;
					c_map1[nx][ny] = d;
					c_map2[nx][ny] = (d+1)%4;
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
		
	
		
		c_map1[1][1] = 2;
		c_map2[1][1] = 2;
		
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(c_map1[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(c_map2[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		int x = n/2 + 1;
		int y = n/2 + 1;
		int dir = 0;
		int sum = 0;
		for(int p=1;p<=k;p++) {

			// 도망자 이동
			ArrayList[][] monster = new ArrayList[n+1][n+1];
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					monster[i][j] = new ArrayList<Integer>();
				}
			}
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(m_map[i][j].size() == 0) continue;
					for(int n_d:m_map[i][j]) {
						if(Math.abs(i-x) + Math.abs(j-y) <= 3) {
							// 1칸 이동 -> 격자
							int ni = i + dx[n_d];
							int nj = j + dy[n_d];
								
							if(ni < 1 || ni > n || nj < 1 || nj > n) {
								if(n_d % 2 == 0) {
									n_d++;
								} else {
									n_d--;
								}
								
								if(x != ni+dx[n_d] || y != nj+dy[n_d]) {
									ni = i + dx[n_d];
									nj = j + dy[n_d];
								} 
							} 
							if(x != ni || y != nj) {
								monster[ni][nj].add(n_d);
							} else {
								monster[i][j].add(n_d);
							}
						
						} else {
							monster[i][j].add(n_d);
						}
					}
					
				}
			}
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					m_map[i][j] = monster[i][j];
				}
			}
			
			
			if(!turn) {
				dir = c_map1[x][y];
				x += c_dx[dir];
				y += c_dy[dir];
				dir = c_map1[x][y];
			} else {
				dir = c_map2[x][y];
				x += c_dx[dir];
				y += c_dy[dir];
				dir = c_map2[x][y];
			}
			if((x == 1 && y == 1) || (x == (n/2+1) && y == (n/2+1))) {
				turn = turn?false:true;
			}
			//System.out.println(dir+" "+x+" "+y);
			int count = 0;
			// 점수 획득
			for(int s=0;s<=2;s++) {
				int s_x = x + c_dx[dir] * s;
				int s_y = y + c_dy[dir] * s;
				if(s_x < 1 || s_x > n || s_y < 1 || s_y > n) continue;
				if(m_map[s_x][s_y].size() != 0 && !tree[s_x][s_y]) {
					count += m_map[s_x][s_y].size();
					m_map[s_x][s_y].clear();
					
				}
			}
			sum += (p*count);
		}
		
		System.out.println(sum);
		
	}
}
