// BOJ - 테트로미노 (14500)
// 구현

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_14500 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		
		String[] s1 = br.readLine().split(" ");
		int n = Integer.parseInt(s1[0]);
		int m = Integer.parseInt(s1[1]);
		int[][] map = new int[n][m];
		
		
		
		for(int i=0;i<n;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int ans = 0;
		
		int[][][] block = {
				{{0, 1}, {1, 0}, {1, 1}}, // ㅁ
				{{0, 1}, {0, 2}, {0, 3}}, // ㅡ
				{{1, 0}, {2, 0}, {3, 0}}, // ㅣ
				{{1, 0}, {2, 0}, {2, 1}}, // L
				{{0, 1}, {0, 2}, {1, 0}},
				{{0, 1}, {1, 1}, {2, 1}},
				{{0, 1}, {0, 2}, {-1, 2}},
				{{1, 0}, {2, 0}, {2, -1}},
				{{0, 1}, {0, 2}, {1, 2}},
				{{0, 1}, {1, 0}, {2, 0}},
				{{1, 0}, {1, 1}, {1, 2}},
				{{1, 0}, {1, 1}, {2, 1}},// Z
				{{0, 1}, {1, 0}, {1, -1}},
				{{0, 1}, {1, 1}, {1, 2}},
				{{1, -1}, {1, 0}, {2, -1}},
				{{0, 1}, {0, 2}, {1, 1}}, // ㅜ
				{{1, 0}, {1, -1}, {2, 0}},
				{{1, 0}, {1, -1}, {1, 1}},
				{{1, 0}, {1, 1}, {2, 0}}
		};
		
		
		for(int b =0;b<block.length;b++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					int score = map[i][j];
					boolean check = true;
					for(int k=0;k<3;k++) {
						int nx = i + block[b][k][0];
						int ny = j + block[b][k][1];
						
						if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
							check = false;
							break;
						}
						score += map[nx][ny];
					}
					if(check) {
						ans = Math.max(ans, score);
					}
					
				}
			}
		}
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		
	}

}
