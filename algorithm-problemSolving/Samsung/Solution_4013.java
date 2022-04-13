// SWEA - 특이한 자석(4013번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_4013 {
	public static ArrayList<ArrayList<Integer>> wheel;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			int k = Integer.parseInt(br.readLine());
			wheel = new ArrayList<>();
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				ArrayList<Integer> list = new ArrayList<>();
				for(int j=0;j<8;j++) {
					list.add(Integer.parseInt(st.nextToken()));
				}
				wheel.add(list);
			}
			
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				rotate_left(num, dir);
				rotate_right(num, dir);
				if(dir == 1) {
					int mag = wheel.get(num-1).get(7);
					wheel.get(num-1).remove(7);
					wheel.get(num-1).add(0, mag);
					
				} else {
					int mag = wheel.get(num-1).get(0);
					wheel.get(num-1).remove(0);
					wheel.get(num-1).add(7, mag);

				}
				

			}
			
			int ans = 0;
			for(int i=0;i<4;i++) {
				if(wheel.get(i).get(0) == 1) {
					ans += Math.pow(2, i);
				} 
			}
			
			

			sb.append("#"+test_case+" ").append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void rotate_left(int num, int dir) {
		if(num == 1) return;
		
		if(wheel.get(num-1).get(6) != wheel.get(num-2).get(2)) {
			rotate_left(num-1, -dir);
			if(-dir == 1) {
				int mag = wheel.get(num-2).get(7);
				wheel.get(num-2).remove(7);
				wheel.get(num-2).add(0, mag);
				
			} else {
				int mag = wheel.get(num-2).get(0);
				wheel.get(num-2).remove(0);
				wheel.get(num-2).add(7, mag);

			}
			
		}
		
		
		
	}
	
	public static void rotate_right(int num, int dir) {

		if(num == 4) return;
		
		if(wheel.get(num-1).get(2) != wheel.get(num).get(6)) {
			rotate_right(num+1, -dir);
			if(-dir == 1) {
				int mag = wheel.get(num).get(7);
				wheel.get(num).remove(7);
				wheel.get(num).add(0, mag);
				
			} else {
				int mag = wheel.get(num).get(0);
				wheel.get(num).remove(0);
				wheel.get(num).add(7, mag);

			}
		}
		
		
	}

}
