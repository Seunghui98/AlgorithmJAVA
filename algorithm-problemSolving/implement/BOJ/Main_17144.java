// BOJ - 미세먼지 안녕!(17144번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17144 {
    public static int R, C, T;
    public static int[][] map;
    public static int m_x[] = {-1, -1};
    // 하, 우, 상, 좌
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0 ,-1};
    // 상,우,하,좌
    public static int[] r_1 = {2, 1, 0, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    if(m_x[0] == -1){
                        m_x[0] = i;
                    } else {
                        m_x[1] = i;
                    }
                }
            }
        }

        while (T-- > 0){
                // 미세먼지 확산
                int[][] map_copy = new int[R][C];
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        map_copy[i][j] = map[i][j];
                    }
                }
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        if(map[i][j] == -1) {
                            continue;
                        }
                        if(map[i][j] == 0) continue;
                        int cnt = 0;

                        for(int d=0;d<4;d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if (0 <= nx && nx < R && 0 <= ny && ny < C && map[nx][ny] != -1) {
                                cnt++;
                                map_copy[nx][ny] += map[i][j] / 5;
                            }
                        }
                        map_copy[i][j] -= (map[i][j] / 5) * cnt;
                        if(map_copy[i][j] < 0) map_copy[i][j] = 0;

                    }
                }
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        map[i][j] = map_copy[i][j];
                    }

                }


                // 공기청정기 가동
                // 반시계

                int x = m_x[0];
                int y = 0;
                for(int d=0;d<4;d++){
                    while (true){
                        int nx = x + dx[r_1[d]];
                        int ny = y + dy[r_1[d]];
                        if(nx< 0 || nx >=R || ny < 0 || ny >= C){
                            break;
                        }
                        if(map[nx][ny] == -1){
                            break;
                        }

                        if(nx >= m_x[1]){
                            break;
                        }
                        if(map[x][y] != -1)
                            map[x][y] = map[nx][ny];
                        else
                            map[x][y] = 0;
                        x = nx;
                        y = ny;
                    }
                }
                map[m_x[0]][0] = -1;
                // 시계
                x = m_x[1];
                y = 0;
                for(int d=0;d<4;d++){
                while (true){
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if(nx< 0 || nx >=R || ny < 0 || ny >= C){
                        break;
                    }
                    if(map[nx][ny] == -1){
                        map[x][y] = 0;
                        break;
                    }

                    if(nx <= m_x[0]){
                        break;
                    }
                    if(map[x][y] != -1)
                        map[x][y] = map[nx][ny];

                    x = nx;
                    y = ny;
                }
            }


        }
        int ans = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == -1) continue;
                ans += map[i][j];
            }
        }
        System.out.println(ans);

    }
}
