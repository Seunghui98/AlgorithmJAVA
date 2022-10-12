import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_23291 {
	public static int n, k;
	public static int[][] map;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) {
			map[n-1][i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		while(true) {
			if(check()) break;
			cnt++;
			
			// 가장 적은 곳에 한 마리
			ArrayList<Integer>list = find_min();
			for(int i:list) {
				map[n-1][i]++;
			}
		
			// 어항 쌓기1
			build_fish1();
	
			// 물고기 수 조절
			moving();
	
			// 일자
			line();
		
			// 어항 쌓기2
			build_fish2();
	
			// 물고기 수 조절
			moving();
			// 일자
			line();
		
			
		}
		System.out.println(cnt);

	}
	
	public static void print() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void build_fish2() {
		int mid = n/2;
		for(int j=0;j<mid;j++) {
			map[n-2][n-1-j] = map[n-1][j];
			map[n-1][j] = 0;
		}

		int mid2 = mid /2;
		for(int j=mid;j<mid+mid2;j++) {
			for(int i=n-1;i>=n-2;i--) {
				map[n-4+(n-1)-i][n-1+mid-j] = map[i][j];
				map[i][j] = 0;
			}
		}
	}
	
	public static void build_fish1() {
		int w = 1;
		int h = 1;
		int x = n-1;
		int y = 0;
		int cnt = 0;
		while(y+w+h <= n) {
			for(int j=y;j<y+w;j++) {
				for(int i=n-1;i>=n-h;i--) {
					map[n-1-w+j-y][y+w+(n-1)-i] = map[i][j];
					map[i][j] = 0;
				}
			}
			cnt++;
			y += w;
			if(cnt % 2 == 1) {
				h++;
			} else {
				w++;
			}
		}
		
	}
	

	public static void moving() {
		int[][] map2 = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 0) continue;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(map[nx][ny] == 0) continue;
					if(map[i][j] < map[nx][ny]) continue;
					int diff = map[i][j] - map[nx][ny];
					if((diff / 5) > 0) {
						map2[i][j] -= diff/5;
						map2[nx][ny] += diff/5;
					}
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] += map2[i][j];
			}
		}
	}
	
	public static void line() {
		ArrayList<Integer> list = new ArrayList<Integer>();
	
		for(int j=0;j<n;j++) {
			for(int i=n-1;i>=0;i--) {
				if(map[i][j] == 0) break;
				list.add(map[i][j]);
			}
		}
		
		int[][] map2 = new int[n][n];
		for(int i=0;i<list.size();i++) {
			map2[n-1][i] = list.get(i);
		}
		map = map2;
	}
	
	public static ArrayList<Integer> find_min(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		int min_value = 10001;
		for(int i=0;i<n;i++) {
			if(map[n-1][i] < min_value) {
				min_value = map[n-1][i];
				list.clear();
				list.add(i);
			} else if(map[n-1][i] == min_value) {
				list.add(i);
			}
		}
		return list;
	}
	
	public static boolean check() {
		int max_value = 0;
		int min_value = 10001;
		for(int i=0;i<n;i++) {
			max_value = Math.max(max_value, map[n-1][i]);
			min_value = Math.min(min_value, map[n-1][i]);
		}
		if(max_value - min_value <= k) return true;
		return false;
	}

}
