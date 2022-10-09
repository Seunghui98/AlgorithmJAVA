import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_47 {
	public static int n, m, k;
	public static int[][] map;
	public static int[][] check;
	public static class Node {
		int x;
		int y;
		boolean d;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, boolean d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static ArrayList<ArrayList<Node>> group;
	public static Node[] team;
	
	// 우 -> 상 -> 좌 -> 하
	public static int[] dx = {0, -1, 0, 1};
	public static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		team = new Node[m];
		map = new int[n][n];
		check = new int[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(check[i], -1);
		}
		group = new ArrayList<>();
		int t_cnt = 0;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					ArrayList<Node> g = new ArrayList<Node>();
					group.add(g);
					team[t_cnt++] = new Node(i, j, false);
				}
			}
		}
		
		// 그룹 짓기
		for(int i=0;i<m;i++) {
			bfs(team[i].x, team[i].y, i);
		}

		int score = 0;
		for(int i=0;i<k;i++) {
			// 이동
			moving();
			// 공 던지기
			Node node = null;
			//System.out.println(i % (4*n));
			if(i % (4*n) < n) {
				node = right(i % (4*n));
				//System.out.println("right");
			} else if(i % (4*n) < 2*n) {
				node = up(i % (4*n) -n);
				//System.out.println("up");
			} else if(i % (4*n) < 3*n) {
				node = left(i % (4*n) - 2*n);
				//System.out.println("left");
			} else {
				node = down(i % (4*n) - 3*n);
				//System.out.println("down");
			}
			
			if(node != null) {
				int g_num = check[node.x][node.y];
				// 점수 증가
				int order = 0;

				if(team[g_num].d) {
					for(int p=group.get(g_num).size()-1;p>=0;p--) {
						Node g_node = group.get(g_num).get(p);
						if(g_node.x == node.x && g_node.y == node.y) {
							score += (p+1)*(p+1);
							break;
						}
					}
					map[node.x][node.y] = 2;
					Node last = team[g_num];
					map[last.x][last.y] = 3;
					Node first = group.get(g_num).get(0);
					map[first.x][first.y] = 1;
					team[g_num] = new Node(first.x, first.y, false);
				} else {
					for(int p=0;p<group.get(g_num).size();p++) {
						Node g_node = group.get(g_num).get(p);
						if(g_node.x == node.x && g_node.y == node.y) {
							score += (p+1)*(p+1);
						}					
					}
					map[node.x][node.y] = 2;
					Node last = team[g_num];
					map[last.x][last.y] = 3;
					Node first = group.get(g_num).get(group.get(g_num).size()-1);
					map[first.x][first.y] = 1;
					team[g_num] = new Node(first.x, first.y, true);
				}
				
			}
			
			
		}
		
		System.out.println(score);
	}
	
	public static Node right(int x) {
		Node node = null;
		for(int j=0;j<n;j++) {
			if(map[x][j] != 4 && map[x][j] != 0 && check[x][j] != -1) {
				return new Node(x, j);
			}
		}
		return node;
	}
	
	public static Node up(int y) {
		Node node = null;
		for(int i=n-1;i>=0;i--) {
			if(map[i][y] != 4 && map[i][y] != 0 && check[i][y] != -1) {
				return new Node(i, y);
			}
		}
		return node;
	}
	
	public static Node left(int x) {
		Node node = null;
		for(int j=n-1;j>=0;j--) {
			if(map[x][j] != 4 && map[x][j] != 0 && check[x][j] != -1) {
				return new Node(x, j);
			}
		}
		return node;
	}
	
	public static Node down(int y) {
		Node node = null;
		for(int i=0;i<n;i++) {
			if(map[i][y] != 4 && map[i][y] != 0 && check[i][y] != -1) {
				return new Node(i, y);
			}
		}
		return node;
	}
	
	
	public static void moving() {
		for(int i=0;i<m;i++) {
			Node first = team[i];
			int dir = find_dir(first.x, first.y);
			int nx = first.x + dx[dir];
			int ny = first.y + dy[dir];
			
			if(first.d) {
				Node last = group.get(i).get(0);
				map[last.x][last.y] = 4;
				group.get(i).remove(0);
				last = group.get(i).get(0);
				map[last.x][last.y] = 3;
				map[nx][ny] = 1;
				map[team[i].x][team[i].y] = 2;
				team[i] = new Node(nx, ny, first.d);
				group.get(i).add(group.get(i).size(), new Node(nx, ny));
			} else {
				Node last = group.get(i).get(group.get(i).size()-1);
				map[last.x][last.y] = 4;
				group.get(i).remove(group.get(i).size()-1);
				last = group.get(i).get(group.get(i).size()-1);
				map[last.x][last.y] = 3;
				map[team[i].x][team[i].y] = 2;
				team[i] = new Node(nx, ny, first.d);
				map[nx][ny] = 1;
				group.get(i).add(0, new Node(nx, ny));
			}
			
		}

	}
	
	public static int find_dir(int x, int y) {
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if(map[nx][ny] == 4) return d;
		}
		return 0;
	}
	
	public static void bfs(int x, int y, int g_num) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		group.get(g_num).add(new Node(x, y));
		check[x][y] = g_num;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(check[nx][ny] !=-1) continue;
				if(map[nx][ny] != 4 && map[nx][ny] != 0) {
					check[nx][ny] = g_num;
					q.add(new Node(nx, ny));
					group.get(g_num).add(new Node(nx, ny));
				}
			}
		}
		
	}

}
