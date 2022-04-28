// BOJ - 게리맨더링
// DFS + BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471 {
	public static int n, ans, sum;
	public static int[] person;
	public static ArrayList<ArrayList<Integer>> connect;
	public static int[] area;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		person = new int[n];
		area = new int[n];
		connect = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) {
			connect.add(new ArrayList<>());
			person[i] = Integer.parseInt(st.nextToken());
			sum += person[i];
		}
		int connect_cnt = 0;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			connect_cnt += m;
			for(int j=0;j<m;j++) {
				connect.get(i).add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
		
		if(connect_cnt == 0) {
			System.out.println(-1);
		} else {
			ans = Integer.MAX_VALUE;
			combination(0);
			
			if(ans== Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(ans);
			}
		}
		
		
	}
	
	public static void combination(int depth) {
		if(depth == n) {
			int area_cnt = 0;
			boolean[] visited = new boolean[n];
			int area1_sum = 0;
			int area2_sum = 0;
			for(int i=0;i<n;i++) {
				if(area[i] == 1) {
					area1_sum += person[i];
				} else {
					area2_sum += person[i];
				}
			}
			for(int i=0;i<n;i++) {
				if(!visited[i]) {
					bfs(i, area[i], visited, area);
					area_cnt++;
				}
			}
		
			if(area_cnt == 2) ans = Math.min(ans, Math.abs(area1_sum-area2_sum));
			return;
		}
		
		// 1번 선거구로 뽑힘
		area[depth] = 1;
		combination(depth+1);
		// 2번 선거구로 뽑힘
		area[depth] = 2;
		combination(depth+1);
	}
	
	public static void bfs(int num, int a, boolean[] visited, int[] area) {
		visited[num] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(num);
		
		while(!q.isEmpty()) {
			int number = q.poll();
		
			for(int i=0;i<connect.get(number).size();i++) {
				int idx = connect.get(number).get(i);
				if(!visited[idx] && area[idx] == a) {
					
					visited[idx] = true;
					q.add(idx);
				}
			}
		}
	}
	
	
	

}
