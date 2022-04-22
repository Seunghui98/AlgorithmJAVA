// BOJ - 아기상어(16236번)
// 구현 + BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_16236{
	public static int n;
	public static int s_x, s_y, s_size;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int[][] map;
	public static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int dis;

		public Fish(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if(this.dis==o.dis) {
				if(this.x == o.x) {
					return this.y- o.y;
				}
				return this.x - o.x;
			} 
			return this.dis - o.dis;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		s_size = 2;
		for(int i=0;i<n;i++) {
			String[] data = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(data[j]);
				if(map[i][j] == 9) {
					map[i][j] = 0;
					s_x = i;
					s_y = j;
				} 
			}
		}
		
		int time = 0;
		int cnt = 0;
		while(true) {
			Fish fish = bfs();

			if(fish == null) {
				break;
			} else {
				map[fish.x][fish.y] = 0;
				s_x = fish.x;
				s_y = fish.y;
				cnt++;
				time += fish.dis;
				if(cnt == s_size) {
					cnt = 0;
					s_size++;
				} 
			}
	
		}
		
		System.out.println(time);

	}
	
	public static Fish bfs() {
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		
		boolean[][] visited = new boolean[n][n];
		Queue<Fish> q = new LinkedList<>();
		q.add(new Fish(s_x, s_y, 0));
		visited[s_x][s_y] = true;
		int move_cnt = 0;
		while (!q.isEmpty()) {
			Fish fish = q.poll();

			for(int d=0;d<4;d++) {
				int nx = fish.x + dx[d];
				int ny = fish.y + dy[d];
				if(0 <= nx && nx < n && 0 <= ny && ny < n) {
					if(!visited[nx][ny] & map[nx][ny] <= s_size) {
						q.add(new Fish(nx, ny, fish.dis+1));
						visited[nx][ny] = true;
						if(map[nx][ny] != 0 && map[nx][ny] < s_size) {
							pq.add(new Fish(nx, ny, fish.dis+1));
						}
					}
				}
			}
			
		}
		if(pq.isEmpty()) {
			return null;
		}
		return pq.poll();
	}

}
