// 삼성 기출 -  백준 - 마법사 상어와 파이어볼
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_20056 {
	public static int n, m, k;
	public static class Node {
		int x;
		int y;
		int m;
		int s;
		int d;
		public Node(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static ArrayList<Node>[][] map;
	public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new ArrayList[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = new ArrayList<Node>();
			}
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r-1][c-1].add(new Node(r-1, c-1, m, s, d));
		}

		for(int c=1;c<=k;c++) {
			ArrayList<Node>[][] copy = new ArrayList[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					copy[i][j] = new ArrayList<Node>();
				}
			}
			// 이동
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					for(int l=0;l<map[i][j].size();l++) {
						Node node = map[i][j].get(l);
						int nx = node.x + dx[node.d] * (node.s% n);
						int ny = node.y + dy[node.d] * (node.s% n);
						if(nx < 0) {
							nx += n;
						} else if(nx >= n) {
							nx -= n;
						}
						
						if(ny < 0) {
							ny += n;
						} else if(ny >= n) {
							ny -= n;
						}
						copy[nx][ny].add(new Node(nx, ny, node.m, node.s, node.d));
					}
				}
			}

			// 이중 -> 복사
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(copy[i][j].size() == 0) continue;
					if(copy[i][j].size() == 1) {
						continue;
					}
					int sum_m = 0;
					int sum_s = 0;
					int cnt = copy[i][j].size();
					int check = 0;
					for(int l=0;l<copy[i][j].size();l++) {
						Node node = copy[i][j].get(l);
						sum_m += node.m;
						sum_s += node.s;
						check += (node.d % 2);
					}
					copy[i][j].clear();
					if((sum_m / 5) <= 0) continue;
					int n_m = sum_m / 5;
					int n_s = sum_s / cnt;
					
					if(check == 0 || check == cnt) {
						for(int d=0;d<8;d+=2) {
							copy[i][j].add(new Node(i, j, n_m, n_s, d));
						}
					} else {
						for(int d=1;d<8;d+=2) {
							copy[i][j].add(new Node(i, j, n_m, n_s, d));
						}
					}
				}
			}
			map = copy;
		}
		
		long sum = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int l=0;l<map[i][j].size();l++) {
					Node node = map[i][j].get(l);
					sum += node.m;
				}
			}
		}
		System.out.println(sum);
	}
	
	public static void print(ArrayList<Node>[][] mm) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(mm[i][j].size()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
