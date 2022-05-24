// BOJ - 도시 건설(21924번)
// MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21924 {
	public static int n, m;
	
	public static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int dis;
		public Edge(int a, int b, int dis) {
			this.a = a;
			this.b = b;
			this.dis = dis;
		}
		@Override
		public int compareTo(Edge o) {
			return this.dis - o.dis;
		}
	}
	
	public static int[] parent;

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		parent = new int[n+1];

		
		for(int i=0;i<=n;i++) {
			parent[i] = i;
		}
		long sum = 0;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			sum += c;
			pq.add(new Edge(a, b, c));
		
		}
		
		long cost = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(find_parent(edge.a) != find_parent(edge.b)) {
				cost += edge.dis;
				union(edge.a, edge.b);
			}
		}
		
		
		boolean chk = false;
		for(int i=1;i<=n;i++) {
			if(find_parent(i) != 1) {
				chk = true;
				break;
			}
		}
		
		if(chk) {
			System.out.println(-1);
		} else {
			System.out.println(sum-cost);
		}
		
		
	}
	
	public static int find_parent(int x) {
		if(parent[x] == x)
			return x;
		return parent[x] = find_parent(parent[x]);
	}
	
	public static void union(int a, int b) {
		a = find_parent(a);
		b = find_parent(b);
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

}
