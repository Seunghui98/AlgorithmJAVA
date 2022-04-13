// BOJ - 낚시왕(17143번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17143 {
	public static int R, C, M;
	public static class Node {
		int s;
		int d;
		int z;
		public Node(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	public static Node[][] map;

	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int ans = 0;
		map = new Node[R][C];

		if(M==0) {
			System.out.println(ans);
			System.exit(0);
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = new Node(s, d-1, z);
		}
		
		for(int j=0;j<C;j++) {
			// 낚시~_~
			for(int i=0;i<R;i++) {
				if(map[i][j] != null) {
					Node node = map[i][j];
					ans += node.z;
					map[i][j] = null;
					break;
				}
			}
			
			Node[][] map_copy = new Node[R][C];
			
			for(int x=0;x<R;x++) {
				for(int y=0;y<C;y++) {
					if(map[x][y] != null) {
						Node node = map[x][y];
						int nx = x;
						int ny = y;
						int move_cnt = node.s;
						int dir = node.d;
						while(move_cnt > 0) {
							nx += dx[dir];
							ny += dy[dir];
							if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
								if(dir % 2 == 0) {
									dir += 1;
								} else {
									dir -= 1;
								}
								nx += dx[dir];
								ny += dy[dir];
								continue;
							}
							move_cnt--;
						}
						
						if(map_copy[nx][ny] != null) {
							Node pre = map_copy[nx][ny];

							if(pre.z < node.z) {
								map_copy[nx][ny] = new Node(node.s, dir, node.z);

							} 
						} else {
							map_copy[nx][ny] = new Node(node.s, dir, node.z);
						}
						
					}
				}
			}
			map = map_copy;

			
		}
		
		System.out.println(ans);
		
	}

}
