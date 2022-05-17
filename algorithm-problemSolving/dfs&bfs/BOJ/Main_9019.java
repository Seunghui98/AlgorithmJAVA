// BOJ - DSLR(9019번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019 {
	public static int A, B;
	public static String ans;
	public static boolean[] visited;
	public static class Node {
		String dir;
		int n;
		public Node(String dir, int str) {
			this.dir = dir;
			this.n = str;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=0;tc<TC;tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			ans = bfs();
			sb.append(ans+"\n");
			
		}
		System.out.println(sb.toString());

	}
	
	public static String bfs() {
		String ans = "";
		Queue<Node> q = new LinkedList();
		q.add(new Node("", A));
		visited = new boolean[10000];
		visited[A] = true;
		while(!q.isEmpty()) {
			int q_len = q.size();
	
			for(int i=0;i<q_len;i++) {
				Node node = q.poll();

				if(node.n == B) {
					return node.dir;
				}
				

				
				// D
				int s = cal(node.n, 'D');
			
				if(!visited[s]) {
				q.add(new Node(node.dir+"D", s));
				visited[s] = true;
				}
				// S
				
				s = cal(node.n, 'S');
			
				if(!visited[s]) {
					q.add(new Node(node.dir+"S", s));
					visited[s] = true;
					}
				// L
				s = cal(node.n, 'L');
		
				if(!visited[s]) {
					q.add(new Node(node.dir+"L", s));
					visited[s] = true;
					}
				// R
				s = cal(node.n, 'R');
		
				if(!visited[s]) {
					q.add(new Node(node.dir+"R", s));
					visited[s] = true;
				}
				
			}
		}
		
		return ans;
	}
	
	public static int cal(int num, char dir) {
		if(dir == 'D') {
			num = (2 * num) % 10000;
			return num;
		} else if(dir == 'S') {
			num = (num==0?9999:num-1);
			return num;
		} else if(dir == 'L') {
			int o = num / 1000;
			int r = (num%1000) / 100;
			int t = ((num%1000) % 100) / 10;
			int f = ((num%1000)%100)%10;
			
			String str_num = String.valueOf(r)+String.valueOf(t)+String.valueOf(f)+String.valueOf(o);
			
			return Integer.parseInt(str_num);
		} else {
			int o = num / 1000;
			int r = (num%1000) / 100;
			int t = ((num%1000) % 100) / 10;
			int f = ((num%1000)%100)%10;
			
			String str_num = String.valueOf(f)+String.valueOf(o)+String.valueOf(r)+String.valueOf(t);
			
			return Integer.parseInt(str_num);
		}
	}

}
