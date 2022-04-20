// BOJ - 드래곤 커브(15685번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15685 {
	public static int[] dx = {0, -1, 0, 1};
	public static int[] dy = {1, 0, -1, 0};
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			visited[x][y] = true;
			ArrayList<Integer> dir_list = new ArrayList<>();
			dir_list.add(d);
			
			
			for(int p=1;p<=g;p++) {
				for(int q=dir_list.size()-1;q>=0;q--) {
					dir_list.add((dir_list.get(q)+1) % 4);
				}
				
			}
			for(int dir:dir_list) {
				x += dx[dir];
				y += dy[dir];
				visited[x][y] = true;
			}
			
		}
		
	
		
		int cnt = 0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
					cnt++;
				
				}
					
			}
		}
		
		System.out.println(cnt);
	}

}
