// SWEA - 미생물 격리(2382번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2382 {
	public static int n, m, k;
	public static int[] dx = {0, -1, 1, 0, 0};
	public static int[] dy = {0, 0, 0, -1, 1};
	public static class Node implements Comparable<Node>{
		int x;
		int y;
		int num;
		int dir;
		public Node(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return -(this.num - o.num);
		}
		
		
	}
	public static ArrayList<Node>[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new ArrayList[n][n];
			
			
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if(map[x][y] == null) {
					map[x][y] = new ArrayList<Node>();
				} 
				map[x][y].add(new Node(x, y, num, dir));
			}
			
			for(int move=0;move<m;move++) {
				ArrayList<Node>[][] map_copy = new ArrayList[n][n];
				
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(map[i][j] == null) continue;
						for(int p=0;p<map[i][j].size();p++) {
							Node node = map[i][j].get(p);
							int nx = node.x + dx[node.dir];
							int ny = node.y + dy[node.dir];
							if(nx <= 0 || nx >= n-1 || ny <= 0 || ny >= n-1) {
								if((node.num/2) == 0) continue;
								if(map_copy[nx][ny] == null) map_copy[nx][ny] = new ArrayList<Node>();
								int dir = node.dir;
								dir = (dir%2==1?dir+1:dir-1);
								map_copy[nx][ny].add(new Node(nx, ny, node.num/2, dir));
							} else {
								if(map_copy[nx][ny] == null) map_copy[nx][ny] = new ArrayList<Node>();
								map_copy[nx][ny].add(new Node(nx, ny, node.num, node.dir));
							}
						}
						
					}
				}
				
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(map_copy[i][j] == null) continue;
						if(map_copy[i][j].size() >= 2) {
							Collections.sort(map_copy[i][j]);
							int sum = 0;
							for(int p=0;p<map_copy[i][j].size();p++) {
								sum += map_copy[i][j].get(p).num;
							}
							
							Node node = map_copy[i][j].get(0);
							map_copy[i][j].clear();
							map_copy[i][j] = new ArrayList<>();
							map_copy[i][j].add(new Node(i, j, sum, node.dir));
						}
					}
				}
				map = map_copy;
			}
			
			int cnt = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] == null) continue;
					cnt += map[i][j].get(0).num;
				}
			}
			sb.append("#"+test_case+" ").append(cnt+"\n");
		}
		System.out.println(sb.toString());

	}
	


}
