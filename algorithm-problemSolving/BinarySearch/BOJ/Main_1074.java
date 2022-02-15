// BOJ - Z(1074번)
// 이진탐색(재귀)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1074 {
    static int ans;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        r = Integer.parseInt(data[1]);
        c = Integer.parseInt(data[2]);

        int nn = (int)Math.pow(2, n);


        ans = 0;
        solution(0, 0, nn);

        System.out.println(ans);

    }
    public static void solution(int x, int y, int len){
        if(x == r && y == c){
            return;
        }

        if(x <= r && r < len/2+x && y <= c && c < len/2+y){
            solution(x, y, len/2);
        } else if(x <= r && r < len/2+x && len/2+y <= c && c < len+y){
            ans += (int)Math.pow(len/2, 2);
            solution(x, y+len/2, len/2);
        } else if(len/2+x <= r && r < len + x && y <= c && c < len/2+y){
            ans += (int)Math.pow(len/2, 2) * 2;
            solution(x+len/2, y, len/2);
        } else if(len/2+x <= r && r < len + x && len/2+y <= c && c < len+y){
            ans += (int)Math.pow(len/2, 2) * 3;
            solution(x+len/2, y+len/2, len/2);
        }

    }
}
