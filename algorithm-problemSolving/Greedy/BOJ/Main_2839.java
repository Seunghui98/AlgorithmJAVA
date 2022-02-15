// BOJ - 설탕배달(2839번)
// 그리디
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_이승희 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        while (true){
            if(n%5 == 0){
                ans += (n/5);
                break;
            } else {
                n -= 3;
                ans++;
            }


            if(n < 0){
                ans = -1;
                break;
            }
        }
        System.out.println(ans);
    }



}
