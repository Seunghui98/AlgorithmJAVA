// SWEA - Ladder1(1210번)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_1210_이승희 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {-1, 0, 0};
        int[] dy = {0, -1, 1};
        int T = 10;
        for(int test_case=1;test_case<=T;test_case++){
            int n = Integer.parseInt(br.readLine());

            String[][] map = new String[100][100];
            boolean[][] visited = new boolean[100][100];
            for(int i=0;i<100;i++){
                String[] data = br.readLine().split(" ");
                map[i] = data;
            }

            int x = 99;
            int y = 0;



            for(int i=0;i<100;i++){
                if(map[99][i].equals("2")){
                    y = i;
                    break;
                }
            }


            int nx = 99;
            int ny = y;
            visited[nx][ny] = true;

            while (true){
                if(nx==0){
                    System.out.println("#"+test_case+" "+ny);
                    break;
                }

                int c_x = nx + dx[1];
                int c_y = ny + dy[1];



                if(0 <= c_x && c_x < 100 && 0 <= c_y && c_y < 100 && map[c_x][c_y].equals("1") && !visited[c_x][c_y]){
                    nx = c_x;
                    ny = c_y;
                    visited[nx][ny] = true;
                    continue;
                }

                c_x = nx + dx[2];
                c_y = ny + dy[2];
                if(0 <= c_x && c_x < 100 && 0 <= c_y && c_y < 100 && map[c_x][c_y].equals("1") && !visited[c_x][c_y]){
                    nx = c_x;
                    ny = c_y;
                    visited[nx][ny] = true;
                    continue;
                }

                nx += dx[0];
                ny += dy[0];
                visited[nx][ny] = true;

            }

        }
    }
}
