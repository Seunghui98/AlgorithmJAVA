import java.io.FileInputStream;
import java.util.Scanner;

public class moving {
	static int T;
	static int A_X, A_Y;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/input2.txt"));
		Scanner scan = new Scanner(System.in);
		int[] dx = {0, -1, 0, 1, 0};
		int[] dy = {0, 0, 1, 0, -1};
		int T = scan.nextInt();
		for(int tc=1;tc<=T;tc++) {
		int n = scan.nextInt();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int num = scan.nextInt();
		int[][] map = new int[n+1][n+1];
		
		for(int i=0;i<num;i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			map[a][b] = -1;
		}
		
		
		int nx = x;
		int ny = y;
		int move = scan.nextInt();
		for(int i=0;i<move;i++) {
			int dir = scan.nextInt();
			int m = scan.nextInt();
			for(int k=0;k<m;k++) {
				nx += dx[dir];
				ny += dy[dir];
				if(nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == -1) {
					nx = 0;
					ny = 0;
					break;
				}
			}
		
		}
		
		System.out.println("#"+tc+" " +nx+" " + ny);
		}
	}

}
