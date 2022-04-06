// BOJ - 게리맨더링(17471번)
// BFS+DFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main_17471 {
	public static int n, all;
	public static int[] person;
	public static int ans_min;
	public static ArrayList<ArrayList<Integer>> list;
	public static ArrayList<ArrayList<Integer>> combi;
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 n = Integer.parseInt(br.readLine());
		 StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		 person = new int[n+1];
		 list = new ArrayList<ArrayList<Integer>>();
		 list.add(new ArrayList<Integer>());
		 all = 0;
		 for(int i=1;i<=n;i++) {
			 list.add(new ArrayList<Integer>());
			 person[i] = Integer.parseInt(st.nextToken());
			 all += person[i];
		 }
		 
		 for(int i=1;i<=n;i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			 int link_len = Integer.parseInt(st.nextToken());
			 
			 for(int j=0;j<link_len;j++) {
				 int node_idx = Integer.parseInt(st.nextToken());
				 list.get(i).add(node_idx);
			 }
		 }

		 
		 ans_min = Integer.MAX_VALUE;
		 combi = new ArrayList<ArrayList<Integer>>();
		 for(int i=1;i<=n-1;i++) {
			 combination(new int[i], 0, i, 0);
		 }
		 
		 for(ArrayList<Integer> c:combi) {
			 if(linkCheck(c)) {  // 해당 조합에서 연결 가능
				if(possibleCheck(c)) {
					int sum = 0;
					for(int idx:c) {
						sum += person[idx];
					}
	
					ans_min = Math.min(ans_min, Math.abs((all-sum)-sum));
				} else {
		
				}
			 } else {

			 }
		 }
		 
		 if(ans_min==Integer.MAX_VALUE) {
			 System.out.println(-1);
		 } else {
		 System.out.println(ans_min);
		 }
		 
		 
	}
	
	public static boolean possibleCheck(ArrayList<Integer> c) {
		boolean[] check = new boolean[n+1];
		for(int idx:c) {
			check[idx] = true;
		}
		int cnt = 1;
		
		for(int i=1;i<=n;i++) {
			if(cnt > 2) return false;
			if(!check[i]) {
				Queue<Integer> q = new LinkedList<>();
				q.add(i);
				check[i] = true;
				cnt += 1;
				while(!q.isEmpty()) {
					int now = q.poll();
					for(int j=0;j<list.get(now).size();j++) {
						int link_node = list.get(now).get(j);
						
						if(!check[link_node]) {
						check[link_node] = true;
						q.add(link_node);
						}
					}
				}
				
			}
		}
	
		if(cnt == 1 || cnt > 2) return false;
		return true;
	}
	
	public static boolean linkCheck(ArrayList<Integer> c) {
		boolean[] check = new boolean[n+1];
		int start_idx = c.get(0);
		Queue<Integer> q = new LinkedList<>();
		q.add(start_idx);
		check[start_idx] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			boolean chk = false;
			for(int idx:c) {
				if(now==idx) {
					chk = true;
					break;
				}
			}
			if(chk) {
				for(int i=0;i<list.get(now).size();i++) {
					int link_node = list.get(now).get(i);
					if(!check[link_node]) {
					check[link_node] = true;
					q.add(link_node);
					}
				}
			}
		}
		
		for(int idx:c) {
			if(!check[idx]) return false;
		}
		
		return true;
	}
	
	public static void combination(int[] arr, int depth, int r, int start) {
		if(depth == r) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			for(int i=0;i<arr.length;i++) {
				c.add(arr[i]);
			}
			combi.add(c);
			return;
		}
		for(int i=start;i<n;i++) {
			arr[depth] = (i+1);
			combination(arr, depth+1, r, i+1);
		}
	}
	
	

}
