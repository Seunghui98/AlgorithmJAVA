// BOJ - 내려가기(2096번)
// DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2096 {
    public static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        StringTokenizer st = null;
        int[][] max_dp = new int[n][3];
        int[][] min_dp = new int[n][3];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<3;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min_dp[i][j] = Integer.MAX_VALUE;
            }
        }


        max_dp[0][0] = map[0][0];
        max_dp[0][1] = map[0][1];
        max_dp[0][2] = map[0][2];

        min_dp[0][0] = map[0][0];
        min_dp[0][1] = map[0][1];
        min_dp[0][2] = map[0][2];
        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
                for(int d=0;d<3;d++){
                    int ny = j + dy[d];
                    if (0 <= ny && ny < 3){
                        max_dp[i][j] = Math.max(max_dp[i-1][ny]+map[i][j], max_dp[i][j]);
                        min_dp[i][j] = Math.min(min_dp[i-1][ny]+map[i][j], min_dp[i][j]);
                    }
                }
            }
        }

        int min_value = Integer.MAX_VALUE;
        int max_value = Integer.MIN_VALUE;
        for(int i=0;i<3;i++){
            max_value = Math.max(max_value, max_dp[n-1][i]);
            min_value = Math.min(min_value, min_dp[n-1][i]);
        }

        System.out.println(max_value+" "+min_value);
    }
}
