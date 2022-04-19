// BOJ - 경사로(14890번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890 {
	public static int n, l;
	public static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로 ->
		int cnt = 0;
		for(int i=0;i<n;i++) {
			if(!check1(map[i])) {
				if(check2(map[i])) {
					cnt++;
				}
			} else {
				cnt++;
			}

		}
		
		// 세로
		for(int j=0;j<n;j++) {
			int[] arr = new int[n];
			for(int i=0;i<n;i++) {
				arr[i] = map[i][j];
			}
			
			if(!check1(arr)) {
				if(check2(arr)) {
					cnt++;
				}
			} else {
				cnt++;
			}
	
		}
		
		System.out.println(cnt);

	}
	
	public static boolean check2(int[] arr) {
		boolean[] install = new boolean[n];
		for(int i=0;i<n-1;i++) {
			if(arr[i] == arr[i+1]) continue;
			else if (Math.abs(arr[i] - arr[i+1]) >= 2) return false;
			else if (arr[i] > arr[i+1]) {
				int temp = arr[i+1];
				for(int j=i+1;j<i+l+1;j++) {
					if(0 <= j && j < n) {
						if(temp != arr[j]) return false;
						if(install[j]) return false;
						install[j] = true;
					} else {
						return false;
					}
				}
			} else if (arr[i] < arr[i+1]) {
				int temp = arr[i];
				for(int j=i;j>=i-l+1;j--) {
					if(0 <= j && j < n) {
						if(temp != arr[j]) return false;
						if(install[j]) return false;
						install[j] = true;
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean check1(int[] arr) {
		int temp= arr[0];
		for(int i=0;i<n;i++) {
			if(temp != arr[i]) return false;
		}
		return true;
	}

}
