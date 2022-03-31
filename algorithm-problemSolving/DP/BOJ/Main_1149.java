// BOJ - RGB 거리 (1149번)
// DP
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][3];
        int[][] color = new int[n+1][3];
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            color[i][0] = r;
            color[i][1] = g;
            color[i][2] = b;
        }

        for(int i=1;i<=n;i++){
            if (i==1){
                dp[i][0] = color[1][0];
                dp[i][1] = color[1][1];
                dp[i][2] = color[1][2];
            } else {
                // R
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + color[i][0];
                // G
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + color[i][1];
                // B
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + color[i][2];
            }
        }
        int min_value = Integer.MAX_VALUE;
        min_value = Math.min(min_value, dp[n][0]);
        min_value = Math.min(min_value, dp[n][1]);
        min_value = Math.min(min_value, dp[n][2]);
        System.out.println(min_value);

    }
}
