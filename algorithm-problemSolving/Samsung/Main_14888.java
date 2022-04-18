// BOJ - 연산자 끼워넣기(14888번)
// DFS(백트래킹)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888 {
	public static int n;
	public static int[] cal;
	public static int[] num;
	public static int ans_max, ans_min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		cal = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<n;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<4;i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
		ans_max = Integer.MIN_VALUE;
		ans_min = Integer.MAX_VALUE;
		dfs(num[0], 0);
		System.out.println(ans_max);
		System.out.println(ans_min);
	}
	
	
	
	public static void dfs(int result, int depth) {
		if(depth == n-1) {
			ans_max = Math.max(ans_max, result);
			ans_min = Math.min(ans_min, result);
			return;
		}
		
		// 덧셈
		if(cal[0] > 0) {
			cal[0] -= 1;
			dfs(result+num[depth+1], depth+1);
			cal[0] += 1;
		}
		// 뺄셈
		if(cal[1] > 0) {
			cal[1] -= 1;
			dfs(result-num[depth+1], depth+1);
			cal[1] += 1;
		}
		// 곱셈
		if(cal[2] > 0) {
			cal[2] -= 1;
			dfs(result*num[depth+1], depth+1);
			cal[2] += 1;
		}
		// 나눗셈
		if(cal[3] > 0) {
			cal[3] -= 1;
			dfs(result/num[depth+1], depth+1);
			cal[3] += 1;
		}
		
		
	}



}
