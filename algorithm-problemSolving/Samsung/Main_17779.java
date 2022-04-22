// BOJ - 게리멘더링2(17779번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17779 {
	public static int[][] map;
	public static int n, total, ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			String[] data = br.readLine().split(" ");
			for(int j=1;j<=n;j++) {
				map[i][j] = Integer.parseInt(data[j-1]);
				total += map[i][j];
			}
		}
		ans = Integer.MAX_VALUE;
		for(int x=1;x<=n;x++) {
			for(int y=1;y<=n;y++) {
				for(int d1=1;d1<=n;d1++) {
					for(int d2=1;d2<=n;d2++) {
						if(x+d1+d2 > n) continue;
						if(y-d1 < 1) continue;
						if(y+d2 > n) continue;
						solve(x, y, d1, d2);
					}
				}
			}
		}
		System.out.println(ans);
		

	}
	
	public static void solve(int x, int y, int d1, int d2) {
		int[][] map2 = new int[n+1][n+1];
		int[] people = new int[5];
		
		// 경계선 그리기
		map2[x][y] = 5;
		for(int i=1;i<=d1;i++) {
			map2[x+i][y-i] = 5;
		}
		
		for(int i=1;i<=d2;i++) {
			map2[x+i][y+i] = 5;
			
		}
		
		for(int i=1;i<=d2;i++) {
			map2[x+d1+i][y-d1+i] = 5;
		}
		
		for(int i=1;i<=d1;i++) {
			map2[x+d2+i][y+d2-i] = 5;
		}
		
		// 1선거구
		for(int i=1;i<x+d1;i++) {
			for(int j=1;j<=y;j++) {
				if(map2[i][j] == 5) break;
				people[0] += map[i][j];
			}
		}
		
		// 2선거구
		for(int i=1;i<=x+d2;i++) {
			for(int j=n;j>y;j--) {
				if(map2[i][j] == 5) break;
				people[1] += map[i][j];
			}
		}
		
		// 3선거구
		for(int i=x+d1;i<=n;i++) {
			for(int j=1;j<y-d1+d2;j++) {
				if(map2[i][j] == 5) break;
				people[2] += map[i][j];
			}
		}
		
		// 4선거구
		for(int i=x+d2+1;i<=n;i++) {
			for(int j=n;j>=y-d1+d2;j--) {
				if(map2[i][j] == 5) break;
				people[3] += map[i][j];
			}
		}
		
		people[4] = total - (people[0] + people[1] + people[2] + people[3]);
		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int i=0;i<5;i++) {
			max = Math.max(max, people[i]);
			min = Math.min(min, people[i]);
		}
		
		ans = Math.min(ans, max-min);
		
		
	}
	
	
	
	}
