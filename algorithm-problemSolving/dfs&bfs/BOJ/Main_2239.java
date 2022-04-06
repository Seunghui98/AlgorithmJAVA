// BOJ - 스도쿠(2239)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Main_2239 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[9][9];

		for(int i=0;i<9;i++) {
			String[] data = br.readLine().split("");
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(data[j]);
			}
		}
		

		boolean check = dfs(map);
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean dfs(int[][] map_copy) {
	
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(map_copy[i][j] != 0) continue;
				for(int num=1;num<=9;num++) {
					if(map_copy[i][j] == 0) {
						if(possibleNumber(i, j, map_copy, num)) {
					
							map_copy[i][j] = num;
							if(dfs(map_copy)) return true;
							
							if(check(map_copy)) {
								return true;
							}
							
							map_copy[i][j] = 0;
						}
					}
				}
				return false;


			}
		}
		return false;
		
	}
	
	public static boolean check(int[][] map) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public static boolean possibleNumber(int row, int col, int[][] map, int num) {
		int row_start = row - (row % 3);
		int col_start = col - (col % 3);
		for(int i=0;i<9;i++) {
			if(map[row][i] == num) return false;
			if(map[i][col] == num) return false;
		}
		
		for(int r=row_start;r<row_start+3;r++) {
			for(int c=col_start;c<col_start+3;c++) {
				if(map[r][c] == num) {
					return false;
				}
			}
		}
		

		return true;
	}
	

}
