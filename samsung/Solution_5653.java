// 삼성 모의 역량 sw - 줄기세포 배양

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5653 {
	public static int n, m, k;
	public static Cell[][] map;
	public static class Node {
		int x;
		int y;
		int live;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, int live) {
			this.x = x;
			this.y = y;
			this.live = live;
		}
	}
	public static class Cell {
		int cnt;
		int live;
		public Cell(int cnt, int live) {
			this.cnt = cnt;
			this.live = live;
		}
	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new Cell[k*2+n+2][k*2+m+2];
			ArrayList<Node> cells = new ArrayList<Node>();
			
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<m;j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num > 0) {
						map[k+i][k+j] = new Cell(num, num);
						cells.add(new Node(k+i, k+j));
					}
				}
			}

			for(int i=0;i<k;i++) {
				ArrayList<Node> new_cells = new ArrayList<Node>();
				for(Node node:cells) {
					Cell c = map[node.x][node.y];
					if(c.cnt > 0) {
						map[node.x][node.y].cnt--;
					} else if(c.cnt == 0) {
						// 번식
						for(int d=0;d<4;d++) {
							int nx = node.x + dx[d];
							int ny = node.y + dy[d];
							if(map[nx][ny] != null) continue;
							new_cells.add(new Node(nx, ny, map[node.x][node.y].live));
						}
						map[node.x][node.y].cnt--;
						map[node.x][node.y].live--;
					} else {
						if(map[node.x][node.y].live >= 0) {
							map[node.x][node.y].live--;
						}
					}
				}
				
				for(Node node:new_cells) {
					if(map[node.x][node.y] != null) {
						if(map[node.x][node.y].live < node.live) {
							map[node.x][node.y].live = node.live;
							map[node.x][node.y].cnt = node.live;
						}
					} else {
						map[node.x][node.y] = new Cell(node.live, node.live);
						cells.add(new Node(node.x, node.y));
					}
				}
				
			}
			
			int cnt = 0;
			for(int i=0;i<k*2+n+2;i++) {
				for(int j=0;j<k*2+m+2;j++) {
					if(map[i][j] != null) {
						if(map[i][j].live > 0) {
							cnt++;
						}
					}
				}
			}
			
			sb.append("#"+test_case+" "+cnt+"\n");
		}
		System.out.println(sb.toString());

	}

}
