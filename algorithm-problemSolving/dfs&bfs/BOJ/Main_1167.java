// BOJ - 트리의 지름(1167번)
// BFS


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static class Node {
		int x;
		int dis;
		public Node(int x, int dis) {
			this.x = x;
			this.dis = dis;
		}
	}
	public static ArrayList<ArrayList<Node>> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0;i<n+1;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int len = st.countTokens()/2+1;
			for(int j=0;j<len;j++) {
				int idx = Integer.parseInt(st.nextToken());
				if(idx == -1) break;
				int dis = Integer.parseInt(st.nextToken());
				list.get(num).add(new Node(idx, dis));
				list.get(idx).add(new Node(num, dis));
			}
			
		}
		
		int[] arr = bfs(1);
		arr = bfs(arr[1]);
		System.out.println(arr[0]);
		
		
	}
	
	public static int[] bfs(int start) {
		int[] arr = new int[2];
		boolean[] visited = new boolean[n+1];
		Queue<Node> q = new LinkedList<>();
		visited[start] = true;
		q.add(new Node(start, 0));
		int max_dis = 0;
		int max_idx = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.dis > max_dis) {
				max_idx = node.x;
				max_dis = node.dis;
				
			}
			
			for(int i=0;i<list.get(node.x).size();i++) {
				Node conNode = list.get(node.x).get(i);
				if(visited[conNode.x]) continue;
				q.add(new Node(conNode.x, conNode.dis+node.dis));
				visited[conNode.x] = true;
			}
		}
		
		arr[0] = max_dis;
		arr[1] = max_idx;
		return arr;
	}

}
