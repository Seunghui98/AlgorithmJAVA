import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine().trim();
		int n = Integer.parseInt(s);
		
		// L, R, U, D
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		String[] move = {"L", "R", "U", "D"};
		
		String[] str = br.readLine().split(" ");
		
		int x = 1;
		int y = 1;

		for(int i=0;i<str.length;i++) {
			String dir = str[i];
			for(int d=0;d<4;d++) {
				if(move[d].equals(dir)) {
					x += dx[d];
					y += dy[d];
					if(x < 1 || y <1 || x > n || y > n) {
						x -= dx[d];
						y -= dy[d];
						continue;
					}
					break;
				}
			}
		}
		
		bw.write(String.valueOf(x +" "+y));
		bw.flush();
		bw.close();
		
	}

}
