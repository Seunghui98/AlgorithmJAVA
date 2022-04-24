// BOJ - 컨베이어 벨트 위의 로봇 (20055번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20055 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> power = new ArrayList<>();
		boolean[] robot = new boolean[n];
	

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<2*n;i++) {
			power.add(Integer.parseInt(st.nextToken()));
			
		}
	
		int time = 1;
		int cnt = 0;
		while(true) {
			// 컨베이어 벨트 한칸  이동
		
			int last = power.get(2*n-1);
			power.remove(2*n-1);
			power.add(0, last);
		
			// 로봇 같이 한칸 이동
			for(int i=n-1;i>=1;i--) {
				robot[i] = robot[i-1];
			}
			robot[n-1] = false;
			robot[0] = false;
		
			// 로봇의 이동
			for(int i=n-1;i>=1;i--) {
			
				int p = power.get(i);
				if(p > 0 && !robot[i] && robot[i-1]) {
					robot[i] = true;
					power.set(i, p-1);
					robot[i-1] = false;
					if(p-1 == 0) {
						cnt++;
					}
				}
			}

			
			int first_p = power.get(0);
			if(first_p > 0) {
				power.set(0, first_p-1);
				robot[0] = true;
				if(first_p == 1) {
					cnt++;
				}
			}
			
			if(cnt >= k) {
				break;
			}

			time++;
		}
		System.out.println(time);
		

	}
	
	public static void print(boolean[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]?'t':'f');
			System.out.print(" ");
		}
		System.out.println();
	}

}
