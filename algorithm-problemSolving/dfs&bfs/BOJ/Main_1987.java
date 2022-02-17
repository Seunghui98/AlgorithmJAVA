// BOJ - 알파벳(1987번)
// DFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_1987 {
    public static int r, c, ans;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static char[][] map;
    public static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        r = Integer.parseInt(data[0]);
        c = Integer.parseInt(data[1]);
        map = new char[r][c];
        visited = new boolean[r][c];
        for(int i=0;i<r;i++){
            map[i] = br.readLine().toCharArray();
        }
        ans = 0;
        boolean[] alpha = new boolean[26];
        alpha[map[0][0]-'A'] = true;
        dfs(0, 0, alpha, 1);

        System.out.println(ans);

    }


    public static void dfs(int x, int y, boolean[] alpha, int depth){
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(0 <= nx && nx < r && 0 <= ny && ny < c){
                if(!visited[nx][ny]){
                    char alpha_c = map[nx][ny];
                    if(!alpha[alpha_c-'A']){
                        visited[nx][ny] = true;
                        alpha[alpha_c-'A'] = true;
                        dfs(nx, ny, alpha, depth+1);
                        alpha[alpha_c-'A'] = false;
                        visited[nx][ny] = false;
                    }
                }
            }
        }

        ans = Math.max(ans, depth);
    }
}
