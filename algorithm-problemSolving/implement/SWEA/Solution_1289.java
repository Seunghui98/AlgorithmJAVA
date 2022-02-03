// SWEA - 원재의 메모리 복구하기(1289)
// 구현
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_1289 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		int tc = Integer.parseInt(s);
		for(int t=1;t<=tc;t++) {
			int cnt = 0;
			String[] data = br.readLine().split("");
			for(int i=0;i<data.length;i++) {
				if(data[i].equals("1")) {
					cnt++;
					for(int j=i;j<data.length;j++) {
						if(data[j].equals("0"))
							data[j] = "1";
						else if(data[j].equals("1"))
							data[j] = "0";
					}
				}
				int zero_cnt = 0;
				for(int d=0;d<data.length;d++) {
					if(data[d].equals("0")) {
						zero_cnt++;
					}
				}
				if(zero_cnt == data.length) {
					break;
				}
			}
			System.out.printf("#%d %d%n", t, cnt);
		}
	}

}
