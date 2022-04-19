// BOJ - 톱니바퀴(14891번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_14891 {
	public static ArrayList<ArrayList<Integer>> chain;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		chain = new ArrayList<>();
		for(int i=0;i<4;i++) {
			String[] input = br.readLine().split("");
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j=0;j<8;j++) {
				list.add(Integer.parseInt(input[j]));
			}
			chain.add(list);
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			String[] input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]) -1;
			int rotate = Integer.parseInt(input[1]);
			
			// 왼쪽 회전
			rotate_left(num, rotate);
			rotate_right(num, rotate);
			if(rotate == 1) {
				int last = chain.get(num).get(7);
				chain.get(num).remove(7);
				chain.get(num).add(0, last);
				
			} else {
				int first = chain.get(num).get(0);
				chain.get(num).remove(0);
				chain.get(num).add(7, first);
			}
		
		}
		int sum = 0;
		for(int i=0;i<4;i++) {
			if(chain.get(i).get(0) == 1) sum += Math.pow(2, i);
		}
		
		System.out.println(sum);

	}
	
	public static void rotate_left(int num, int rotate) {
		if(num == 0) return;
		
		if(chain.get(num).get(6) != chain.get(num-1).get(2)) {
			rotate = -rotate;
			rotate_left(num-1, rotate);
			
			if(rotate == 1) {
				int last = chain.get(num-1).get(7);
				chain.get(num-1).remove(7);
				chain.get(num-1).add(0, last);
				
			} else {
				int first = chain.get(num-1).get(0);
				chain.get(num-1).remove(0);
				chain.get(num-1).add(7, first);
			}
			
		}
	}
	
	public static void rotate_right(int num, int rotate) {
		if(num == 3) return;
		
		if(chain.get(num).get(2) != chain.get(num+1).get(6)) {
			rotate = -rotate;
			rotate_right(num+1, rotate);
			
			if(rotate == 1) {
				int last = chain.get(num+1).get(7);
				chain.get(num+1).remove(7);
				chain.get(num+1).add(0, last);
				
			} else {
				int first = chain.get(num+1).get(0);
				chain.get(num+1).remove(0);
				chain.get(num+1).add(7, first);
			}
			
		}
	}

}
