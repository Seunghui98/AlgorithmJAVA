// BOJ - 야구(17281번)
// DFS(순열) + 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281 {
	public static int n, max_score;
	public static int[] permu;
	public static boolean[] visited;
	public static int[][] game;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		game = new int[n][10];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<9;j++) {
				game[i][j+1] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[10];
		permu = new int[10];
		permu[4] = 1;
		visited[4] = true;
		max_score = 0;
		permutation(2, permu);
		
		System.out.println(max_score);
		
	}
	
	public static void permutation(int depth, int[] permu) {
		if(depth == 10) {
			play(permu);
			return;
		}
		
		for(int i=1;i<=9;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			permu[i] = depth;
			permutation(depth+1, permu);
			visited[i] = false;
			permu[i] = 0;
			
		}
	}
	
	public static void play(int[] permu) {
		int score = 0;
		int start = 1;
	
	
		
		outer:for(int i=0;i<n;i++) {
			int out = 0;
			boolean[] base = new boolean[4]; // 홈, 1루, 2루, 3루
			while(true) {
				for(int j=start;j<=9;j++) {
					if(game[i][permu[j]] == 0) {
						out++;
					} else if(game[i][permu[j]] == 1) {
						base[0] = true;
						for(int b=3;b>=0;b--) {
							if(base[b]) {
								if(b==3) {
									score++;
									base[b] = false;
								} else {
									base[b+1] = true;
									base[b] = false;
								}
							}
						}
					} else if(game[i][permu[j]] == 2) {
						base[0] = true;
						for(int b=3;b>=0;b--) {
							if(base[b]) {
								if(b==3 || b==2) {
									score++;
									base[b] = false;
								} else {
									base[b+2] = true;
									base[b] = false;
								}
							}
						}
					} else if(game[i][permu[j]] == 3) {
						base[0] = true;
						for(int b=3;b>=0;b--) {
							if(base[b]) {
								if(b==3 || b==2 || b==1) {
									score++;
									base[b] = false;
								} else {
									base[b+3] = true;
									base[b] = false;
								}
							}
						}
					} else if(game[i][permu[j]] == 4) {
						base[0] = true;
						for(int b=3;b>=0;b--) {
							if(base[b]) {
								score++;
								base[b] = false;
							}
						}
					
					}
					if(out == 3) {
						start = (j==10?1:j+1);
						continue outer;
					}
				}
				start = 1;
				
			}
		}
		max_score = Math.max(max_score, score);
	}

}
