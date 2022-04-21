// BOJ - 나무 재테크 (16235번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16235 {
	public static int n, m, k;
	public static int[][] add;
	public static int[][] map;
	public static class Tree implements Comparable<Tree>{
		int x;
		int y;
		int age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
		
	}
	public static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
	public static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
	public static Queue<Tree> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		add = new int[n][n];
		map = new int[n][n];
		q = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			q.add(new Tree(x-1, y-1, age));
		}
		
		// 초반에만 정렬하면 더이상 정렬 필요 x
		Collections.sort((List<Tree>)q);
		
		for(int a=0;a<k;a++) {
			ArrayList<Tree> dead = new ArrayList<>();
			// 봄
			int q_len = q.size();
			
			while(q_len-- > 0) {
				Tree tree = q.poll();
				if(tree.age <= map[tree.x][tree.y]) {
					map[tree.x][tree.y] -= tree.age;
					q.add(new Tree(tree.x, tree.y, tree.age+1));
				} else {
					dead.add(new Tree(tree.x, tree.y, tree.age));
				}
			}

			
			
			// 여름
			for(Tree tree:dead) {
				map[tree.x][tree.y] += tree.age/2;
			}
			ArrayList<Tree> parent = new ArrayList<>();
			q_len = q.size();
			// 가을
			while(q_len-- > 0) {
				Tree tree = q.poll();
				parent.add(tree);
				if(tree.age % 5 == 0) {
					for(int d=0;d<8;d++) {
						int nx = tree.x + dx[d];
						int ny = tree.y + dy[d];
						if(0 <= nx && nx < n && 0 <= ny && ny < n) {
							q.add(new Tree(nx, ny, 1));
						}
					}
				}
			}
			
			for(Tree tree:parent) {
				q.add(tree);
			}
			
			// 겨울
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] += add[i][j];
				}
			}
		

		
		}
		
		
		System.out.println(q.size());
	}

}
