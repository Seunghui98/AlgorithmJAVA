// BOJ - 샘터(18513번)
// BFS


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main_18513 {
	public static int n, k;
	public static Set<Integer> visited;
	public static class Node {
		int x;
		int dist;
		public Node(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}
	}
	public static Queue<Node> q;
	public static int[] dx = {-1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] data = br.readLine().split(" ");
		n = Integer.parseInt(data[0]);
		k = Integer.parseInt(data[1]);
		q = new LinkedList<>();
		visited = new HashSet<Integer>();
		data = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(data[i]);
			q.add(new Node(x, 0));
			visited.add(x);
		}
		
		long ans = bfs();
		System.out.println(ans);
	}
	
	public static long bfs() {
		long dist = 0;
		int cnt = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<2;d++) {
				int nx = node.x + dx[d];
				//if(nx < -100000000 || nx > 100000000) continue;
				
				if(visited.contains(nx)) continue;
				
				dist += (node.dist+1);
				cnt++;
				visited.add(nx);
				
				if(cnt == k) return dist;
				q.add(new Node(nx, node.dist+1));
			}
		}
		
		return dist;
	}
	
	

}
