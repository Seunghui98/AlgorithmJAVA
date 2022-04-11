// BOJ - 테트로미노(14500번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500 {
	
	public static int[][][] tetromino = 
		{{{1, 1, 1, 1}}, //ㅡ 
		 {{1}, {1}, {1}, {1}}, //ㅣ
		 {{1, 1},{1, 1}}, //ㅁ
		 {{1, -1}, {1, -1}, {1, 1}}, //L
		 {{1, 1, 1}, {1, -1, -1}},
		 {{1, 1}, {-1 ,1}, {-1, 1}},
		 {{-1, -1, 1}, {1, 1, 1}},
		 {{-1, 1}, {-1, 1}, {1, 1}},
		 {{1, 1, 1}, {-1, -1, 1}},
		 {{1, 1}, {1, -1}, {1, -1}},
		 {{1, -1, -1}, {1, 1, 1}},
		 {{1, -1}, {1, 1}, {-1, 1}}, //Z
		 {{-1, 1, 1}, {1, 1, -1}},
		 {{-1, 1}, {1, 1}, {1, -1}},
		 {{1, 1, -1}, {-1, 1, 1}},
		 {{1, 1, 1}, {-1, 1, -1}}, //ㅜ
		 {{-1 ,1}, {1, 1}, {-1, 1}},
		 {{-1, 1, -1}, {1, 1, 1}},
		 {{1, -1}, {1, 1}, {1, -1}},
		 };
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				outer:for(int t=0;t<tetromino.length;t++) {
					int sum = 0;
					for(int p=0;p<tetromino[t].length;p++) {
						for(int q=0;q<tetromino[t][p].length;q++) {
							int x = i + p;
							int y = j + q;
							
							if(x < 0 || x >= n || y < 0 || y >=m) continue outer;
							if(tetromino[t][p][q] == 1) {
								sum += map[x][y];
							}
						}
					}
					ans = Math.max(ans, sum);
				}
			}
		}
		
		System.out.println(ans);
		

	}

}
