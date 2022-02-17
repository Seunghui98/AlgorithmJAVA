// BOJ - 빵집(3109번)
// DFS(백트래킹)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3109 {
    public static int R, C;
    public static char[][] map;

    public static int ans;
    public static boolean check;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        R = Integer.parseInt(data[0]);
        C = Integer.parseInt(data[1]);
        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0;i<R;i++){
            map[i] = br.readLine().toCharArray();
        }
        ans = 0;

        for(int i=0;i<R;i++){
            if(map[i][0] == '.') {
                check = false;
                dfs(i, 0);
            }
        }
        System.out.println(ans);

    }

    public static void dfs(int x, int y){
        if(y == C-1){
            check = true;
            ans++;
            return;
        }

        for(int d=0;d<3;d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (0 <= nx && nx < R & 0 <= ny && ny < C) {
                if (!visited[nx][ny] && map[nx][ny] == '.' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny);

                    if(check){
                        return;
                    }
                }
            }

        }
    }




}
