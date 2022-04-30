// SWEA - 핀볼게임(5650번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_5650 {
	public static int n, s_x, s_y;
	public static int[][] map;
	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0 ,0, -1, 1};
	public static HashMap<Integer, Node> hash;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		
		for(int test_case=1;test_case<=TC;test_case++) {
			n = Integer.parseInt(br.readLine());
			hash = new HashMap<>();
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]  >= 6 || map[i][j] <= 10) {
						if(hash.containsKey(map[i][j])) {
							hash.put(-map[i][j], new Node(i, j));
						} else {
							hash.put(map[i][j], new Node(i, j));
						}
					}
				}
			}
			
			int max_score = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] != 0) continue;
					for(int d=0;d<4;d++) {
						s_x = i;
						s_y = j;
						int score = game(i, j, d);
						max_score = Math.max(max_score, score);
					}
				}
			}
			
			sb.append("#"+test_case+" "+max_score+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int game(int x, int y, int dir) {
		int score = 0;
		int nx = x;
		int ny = y;
		
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
				score++;
				dir = (dir%2==0?dir+1:dir-1);
				continue;
			}
			
			if(nx == x && ny == y) {
				break;
			}
			
			if(map[nx][ny] == -1) {
				break;
			}
			
			
			if(map[nx][ny] == 1) {
				score++;
				if(dir == 0 || dir == 3) {
					dir = (dir%2==0?dir+1:dir-1);
				} else {
					dir = (dir+2) % 4;
					
				}
			} else if(map[nx][ny] == 2) {
				score++;
				if(dir == 1 || dir == 3) {
					dir = (dir%2==0?dir+1:dir-1);
				} else {
					dir = (dir+3) % 4;
				}
			} else if(map[nx][ny] == 3) {
				score++;
				if(dir == 1 || dir == 2) {
					dir = (dir%2==0?dir+1:dir-1);
				} else {
					dir = (dir+2) % 4;
				}
			} else if(map[nx][ny] == 4) {
				score++;
				if(dir == 0 || dir == 2) {
					dir = (dir%2==0?dir+1:dir-1);
				} else {
					dir = (dir+1) % 4;
				}
			} else if(map[nx][ny] == 5){
				score++;
				dir = (dir%2==0?dir+1:dir-1);
			} else if(map[nx][ny] >= 6 && map[nx][ny] <= 10) {
				int warm_num = map[nx][ny];
				Node node1 = hash.get(warm_num);
				Node node2 = hash.get(-warm_num);
				if(node1.x == nx && node1.y == ny) {
					nx = node2.x;
					ny = node2.y;
				} else {
					nx = node1.x;
					ny = node1.y;
				}
				continue;
			}
			

		}
		
		
		return score;
	}

}
