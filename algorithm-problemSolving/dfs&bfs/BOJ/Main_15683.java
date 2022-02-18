// BOJ - 감시(15683번)
// BFS + 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main_15683 {
    public static int n, m;
    public static int[][] map;
    public static int ans;
    public static ArrayList<CCTV> c_list;

    // 우, 하, 좌, 상
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static int[][][] c_mode = {
            {{},{},{},{}},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 3}, {0, 1}, {1, 2}, {2, 3}},
            {{0, 2, 3}, {0, 1, 3}, {0, 1, 2}, {1, 2, 3}},
            {{0, 1, 2, 3}}
    };

    public static class CCTV{
        int num;
        int x;
        int y;
        public CCTV(int num, int x, int y){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);

        if(n == 1 && m == 1){
            String nn = br.readLine();
            if(nn.equals("0")){
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else {
            map = new int[n][m];
            int zero_cnt = 0;
            c_list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    if (map[i][j] > 0 && map[i][j] < 6) {
                        c_list.add(new CCTV(map[i][j], i, j));
                    }

                    if (map[i][j] == 0) {
                        zero_cnt++;
                    }
                }
            }

            ans = zero_cnt;
            if (c_list.size() > 0) {
                dfs(0, map);
                System.out.println(ans);
            } else {
                System.out.println(zero_cnt);
            }
        }

    }

    public static int[][] fill(int[] mode, int x, int y, int cctv_num, int[][] arr){

        for(int md=0;md<mode.length;md++){

            int nx = x + dx[mode[md]];
            int ny = y + dy[mode[md]];

            while (true){
                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                    break;
                }

                if(arr[nx][ny] == 6){
                    break;
                }
                arr[nx][ny] = -1;
                nx += dx[mode[md]];
                ny += dy[mode[md]];
            }
        }
        return arr;
    }

    public static void dfs(int depth, int[][] arr){
        if(depth == c_list.size()){
            int cnt = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(arr[i][j] == 0) cnt++;
                }
            }



            ans = Math.min(ans, cnt);
            return;
        }

        int[][] temp_arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                temp_arr[i][j] = arr[i][j];
            }
        }

        CCTV cctv = c_list.get(depth);
        for(int[] c_arr:c_mode[cctv.num]){
                    temp_arr = fill(c_arr, cctv.x, cctv.y, cctv.num, temp_arr);
                    dfs(depth+1, temp_arr);
                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            temp_arr[i][j] = arr[i][j];
                        }
                    }

        }

    }

}
