// BOJ - 괄호 추가하기
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16637 {
	public static int n, max_ans;
	public static int[] number;
	public static char[] c;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String data = br.readLine();
		number = new int[n/2+1];
		c = new char[n/2];
		int num_cnt = 0;
		int c_cnt = 0;
		for(int i=0;i<n;i++) {
			char input = data.charAt(i);
			if(i % 2 == 0) {
				number[num_cnt++] = input - '0';
			} else {
				c[c_cnt++] = input;
			}
		}
		max_ans = Integer.MIN_VALUE;
		dfs(number[0], 0);
		System.out.println(max_ans);
	}
	
	public static void dfs(int result, int idx) {
		if(idx == number.length-1) {
			max_ans = Math.max(max_ans, result);
			return;
		}
	
		// 다음에서 괄호 안 치는 경우
		dfs(calcul(result, number[idx+1], c[idx]), idx+1);
		
		if(idx+2 <= number.length-1) {
			dfs(calcul(result, calcul(number[idx+1], number[idx+2], c[idx+1]), c[idx]), idx+2);
		}
	}
	
	public static int calcul(int a, int b, char c_char) {

		if(c_char == '+') {
			return a+b;
		} else if(c_char == '-') {
			return a-b;
		} else {
			return a*b;
		}
	}

}
