// BOJ - 자리 배정(10157번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int c = Integer.parseInt(data[0]);
        int r = Integer.parseInt(data[1]);
        int k = Integer.parseInt(br.readLine());
        int[][] map = new int[r][c];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        if(k > c*r){
            System.out.println(0);
        }  else {
            int nx = r-1;
            int ny = 0;
            int dir = 0;
            int cnt = 1;
            map[nx][ny] = cnt;
            while (cnt != k){
                map[nx][ny] = cnt;
                nx += dx[dir];
                ny += dy[dir];
                if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] != 0){
                    nx -= dx[dir];
                    ny -= dy[dir];
                    dir = (dir+1)%4;
                    continue;
                }

                cnt++;


            }
            System.out.println((ny+1) +" "+(r-nx));
        }
    }
}
