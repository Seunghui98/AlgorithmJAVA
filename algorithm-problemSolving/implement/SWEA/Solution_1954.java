// SWEA - 달팽이 숫자(1954)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int nx = 0;
			int ny = -1;
			int d = 0;
			int num = 1;
			while(num <= n*n) {
				nx += dx[d];
				ny += dy[d];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] != 0) {
					nx -= dx[d];
					ny -= dy[d];
					d = ((d+1) % 4);
					num--;
				}
				map[nx][ny] = num;
				num++;
				
			}
			System.out.println("#"+test_case);
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
