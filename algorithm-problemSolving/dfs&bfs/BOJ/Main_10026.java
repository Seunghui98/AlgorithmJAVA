// BOJ - 적록색맹(10026번)
// DFS

import java.io.*;


public class Main_10026 {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static char[][] map;
    public static boolean[][] visited1;
    public static boolean[][] visited2;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited1 = new boolean[n][n];
        visited2 = new boolean[n][n];

        for(int i=0;i<n;i++){
            String[] data = br.readLine().split("");
            for(int j=0;j<n;j++){
                map[i][j] = data[j].charAt(0);
            }
        }

        int ans1 = 0;
        int ans2 = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited1[i][j]){
                    solution1(i, j, map[i][j]);
                    ans1++;
                }

                if(!visited2[i][j]){
                    solution2(i, j, map[i][j]);
                    ans2++;
                }
            }
        }

        bw.write(String.valueOf(ans1+" "+ans2));
        bw.flush();
        bw.close();

    }

    public static void solution1(int x, int y, char color){
        visited1[x][y] = true;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(0 <= nx && nx < n && 0 <= ny && ny < n){

                if(!visited1[nx][ny] && map[nx][ny] == map[x][y]){
                    solution1(nx, ny, color);
                }
            }
        }
    }

    public static void solution2(int x, int y, char color){
        visited2[x][y] = true;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(0 <= nx && nx < n && 0 <= ny && ny < n){

                if(!visited2[nx][ny]){
                    if(map[nx][ny] == map[x][y]){
                        solution2(nx, ny, color);
                    } else if (color == 'R' || color == 'G'){
                        if(map[nx][ny] == 'R' || map[nx][ny] == 'G'){
                            solution2(nx, ny, map[nx][ny]);
                        }
                    }
                }


            }
        }
    }
}
