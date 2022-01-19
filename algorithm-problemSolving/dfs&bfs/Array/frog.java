package prac_java_01;

import java.io.FileInputStream;
import java.util.Scanner;

public class frog {
	static int Answer;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scan = new Scanner(System.in);
		int[] dx = {0, -1, 1, 0, 0};
		int[] dy = {0, 0, 0, -1, 1};
		
		int T = scan.nextInt();
		for(int test_case=1;test_case<=T;test_case++) {
			int n = scan.nextInt();
			int num = scan.nextInt();
			int[][] map = new int[n+1][n+1];
			int sum = 0;
			for(int i=0;i<num;i++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				int dir = scan.nextInt();
				int nx = x + dx[dir]*6;
				int ny = y + dy[dir]*6;
				if(map[x][y] != 1 && nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1) {
					sum += 1;
					map[nx][ny] = 1;
				}
				
			}
			
			Answer = sum;
			System.out.println("#"+test_case+" "+Answer);
			Answer = 0;
			
			
			
		}
	}

}
