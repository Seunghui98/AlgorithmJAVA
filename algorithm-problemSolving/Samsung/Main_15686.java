// BOJ - 치킨 배달(15686번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686 {
	public static int n, m, ans;
	public static int[][] map;
	public static ArrayList<Node> chick;
	public static ArrayList<Node> home;
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		chick = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine() , " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					home.add(new Node(i, j));
				} else if(map[i][j] == 2) {
					chick.add(new Node(i, j));
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		
		dfs(0, 0, new int[m]);
		
		System.out.println(ans);
	}
	
	
	public static void dfs(int depth, int start, int[] arr) {

		if(depth == m) {
			int distance = dis_cal(arr);
			ans = Math.min(ans, distance);
			return;
		}
		
		for(int i=start;i<chick.size();i++) {
			arr[depth] = i;
			dfs(depth+1, i+1, arr);
		}
	}
	
	public static int dis_cal(int[] arr) {
		int dis_sum = 0;
		for(Node h :home) {
			int distance = 2*n+1;
			for(int idx:arr) {
				Node c = chick.get(idx);
				distance = Math.min(distance, Math.abs(h.x - c.x)+Math.abs(h.y - c.y));
			}
			dis_sum += distance;
		}
		return dis_sum;
		
	}

}
