// BOJ - 단지번호 붙이기(2667번)
// DFS
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class Main_2667 {
    public static int[][] map;
    public static int n, max_cnt;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            String[] data = br.readLine().split("");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(data[j]);
            }
        }

        int cnt = 0;

        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    cnt++;
                    max_cnt = 1;
                    dfs(i, j);
                    list.add(max_cnt);
                }
            }
        }
        Collections.sort(list);
        bw.write(String.valueOf(cnt+"\n"));
        for(int l:list){
            bw.write(String.valueOf(l+"\n"));
        }

        bw.flush();
        bw.close();

    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if( 0 <= nx && nx < n && 0 <= ny && ny < n){
                if(!visited[nx][ny] && map[nx][ny] == 1){
                    max_cnt++;
                    dfs(nx, ny);
                }
            }
        }


    }


}
