// 정올 - 해밀턴 순환회로(1681번)
// DFS(백트래킹)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1681 {
    public static int N;
    public static int[][] map;
    public static int ans;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
        visited = new boolean[N];
        visited[0] = true;
        dfs(0, 0, 0, visited);
        System.out.println(ans);
    }

    public static void dfs(int depth, int sum, int home, boolean[] visited){

        if(depth == N-1){
            if(map[home][0] != 0)
                ans = Math.min(ans, sum+map[home][0]);
            return;
        }


        for(int i=1;i<N;i++){
            if(visited[i]) continue;
            if(map[home][i] == 0) continue;
            if(sum+map[home][i] > ans) continue;
            visited[i] = true;
            dfs(depth+1, sum+map[home][i], i, visited);
            visited[i] = false;
        }
    }
}
