// BOJ - 시험 감독(13485번)
// 구현


import java.util.Scanner;

public class Main_13485 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] arr = new int[n];
		
		for(int i=0;i<n;i++) {
			int num = scan.nextInt();
			arr[i] = num;
		}
		
		int b = scan.nextInt();
		int c = scan.nextInt();
		
		long ans = 0;
		for(int i=0;i<n;i++) {
			int cnt = 1;
			int num = arr[i] - b;
			if(num > 0) {
				cnt += (num % c > 0?(num/c +1):(num/c));
			}
			ans += cnt;
		}
		
		System.out.println(ans);

	}

}
