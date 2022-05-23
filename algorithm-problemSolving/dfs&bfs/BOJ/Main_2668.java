// BOJ - 2668번(숫자 고르기)
// DFS 사이클

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_2668 {
	public static boolean[] visited;
	public static boolean[] chk;
	public static int n;
	public static int cnt;
	public static int[] connect;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		connect = new int[n+1];
		chk = new boolean[n+1];
		for(int i=1;i<=n;i++) {
			int num = Integer.parseInt(br.readLine());
			connect[i] = num;
		}
		cnt = 0;
		
		for(int i=1;i<=n;i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		for(int i=1;i<=n;i++) {
			if(chk[i]) cnt++;
		}
		System.out.println(cnt);
		for(int i=1;i<=n;i++) {
			if(chk[i]) System.out.print(i +" ");
		}
		System.out.println();
	}
	
	public static void dfs(int idx, int start) {
		int connect_num = connect[idx];
		if(!visited[connect_num]) {
			visited[connect_num] = true;
			dfs(connect_num, start);
			visited[connect_num] = false;
		}
		
		if(connect[idx] == start) {
			chk[idx] = true;
			chk[connect_num] = true;
		}
		
	}
}
