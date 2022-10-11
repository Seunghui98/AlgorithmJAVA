package algor_boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23291 {
	public static int n, m;
	public static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) {
			map[n-1][i] = Integer.parseInt(st.nextToken());
		}
		

	}

}
