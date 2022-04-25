// BOJ - 상어 초등학교(21608번)
// 구현 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_21608 {
	public static int[][] map;
	public static int n;

	public static class Seat implements Comparable<Seat>{
		int x;
		int y;
		int like;
		int empty;
		public Seat(int x, int y, int like, int empty) {
			this.x = x;
			this.y = y;
			this.like = like;
			this.empty = empty;
		}
		@Override
		public int compareTo(Seat o) {
			if(this.like == o.like) {
				if(this.empty == o.empty) {
					if(this.x == o.x) {
						return this.y - o.y;
					}
					return this.x - o.x;
				}
				return -(this.empty - o.empty);
			}
			return -(this.like - o.like);
		}
	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int[][] student_like;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		student_like = new int[n*n][5];
		
		for(int i=0;i<n*n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				student_like[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		HashMap<Integer, Integer> hash = new HashMap<>();
		
		// 학생들 자리에 앉기..
		for(int s=0;s<n*n;s++) {
			ArrayList<Seat> list = new ArrayList<Seat>();
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] != 0) continue;
					int like_cnt = 0;
					int empty_cnt = 0;
					for(int d=0;d<4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if(0 <= nx && nx < n && 0 <= ny && ny < n) {
							for(int l=1;l<5;l++) {
								if(map[nx][ny] == student_like[s][l]) {
									like_cnt++;
									break;
								}
							}
							if(map[nx][ny] == 0) empty_cnt++;
						}
					}
				
					list.add(new Seat(i, j, like_cnt, empty_cnt));
				}
			}
			
			Collections.sort(list);

			Seat seat = list.get(0);
			
			map[seat.x][seat.y] = student_like[s][0];
			hash.put(student_like[s][0], s);
		}
		
		int ans = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int s_idx = hash.get(map[i][j]);
				int cnt = 0;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(0 <= nx && nx < n && 0 <= ny && ny < n) {
						for(int k=1;k<=4;k++) {
							if(student_like[s_idx][k] == map[nx][ny]) {
								cnt++;
								break;
							}
						}
					}
				}
				if(cnt!=0) {
					ans += Math.pow(10, cnt-1);
				} 
			}
		}
		
		System.out.println(ans);
		
	}
	


}
