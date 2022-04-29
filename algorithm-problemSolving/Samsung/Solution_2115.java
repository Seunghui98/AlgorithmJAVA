// SWEA - 벌꿀채취(2115번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115 {
	public static int n, m, c, max, sweat;
	public static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 0;
			solve();
			sb.append("#"+test_case+" ").append(max+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<=n-m;j++) {
				int sweat_sum = 0;
				sweat = 0;
				combination(i, j, 0, 0, 0);
				int max1 = sweat;
				
				
				int max2 = 0;
				for(int q=j+m;q<=n-m;q++) {
					sweat = 0;
					combination(i, q, 0, 0, 0);
					max2 = Math.max(max2, sweat);
				}
				
				max = Math.max(max, sweat_sum+sweat);
				
				
				for(int p=i+1;p<n;p++) {
					for(int q=0;q<=n-m;q++) {
						sweat = 0;
						combination(p, q, 0, 0, 0);
						max2 = Math.max(max2, sweat);
					}
				}
				
				max = Math.max(max, max1+max2);
			}
		}
	}
	
	public static void combination(int x, int y, int cnt, int sum1, int sum2) {
		if(sum1 > c) {
			return;
		}
		
		if(cnt == m) {
			sweat = Math.max(sweat, sum2);
			return;
		}
		
		// 선택
		combination(x, y+1, cnt+1, sum1+map[x][y], sum2+map[x][y]*map[x][y]);
		
		// 비선택
		combination(x, y+1, cnt+1, sum1, sum2);
	}

}
