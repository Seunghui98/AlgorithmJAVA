// BOJ - 내리막 길(1520번)
// DP

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1520 {
    public static int n, m;
    public static int[][] map;
    public static int[][] dp;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    // 시간초과 ㅠㅠ
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }


        dfs(0, 0);
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[0][0]);

    }

    public static int dfs(int x, int y){
        if(x==n-1 && y==m-1){
            return 1;
        }

        if(dp[x][y] != -1){
            return dp[x][y];
        }
        dp[x][y] = 0;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(0 <= nx && nx < n && 0 <= ny && ny < m){
                if(map[nx][ny] < map[x][y]){
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }


        return dp[x][y];
    }
}
