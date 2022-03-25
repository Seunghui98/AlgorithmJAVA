// BOJ - 어항정리 (23291번)
// 구현


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_23291 {
    public static int n, k;
    public static int[][] map;
    public static int max, min;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            map[n-1][i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        while (true){
            if(check()){
                break;
            }
            cnt++;

            // 물고기 추가
            for(int i=0;i<n;i++){
                if(map[n-1][i] == min){
                    map[n-1][i] += 1;
                }
            }

            // 물고기 쌓기
            build_fish();

            // 물고기 수 조절
            moving();

            // 일렬로 놓기
            line();

            // 공중부양 2번
            buiid_fish2();

            // 물고기 수 조절
            moving();

            // 일렬로 놓기
            line();


        }
        System.out.println(cnt);


    }
    public static void print(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void line(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int j=0;j<n;j++){
            if(map[n-1][j] == 0) continue;
            for(int i=n-1;i>=0;i--){
                if(map[i][j] == 0) break;
                list.add(map[i][j]);
            }
        }

        int[][] map_copy = new int[n][n];
        for(int j=0;j<list.size();j++){
            map_copy[n-1][j] = list.get(j);
        }
        map = map_copy;
    }

    public static void moving(){
        int[][] map_copy = new int[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){

                if(map[i][j] != 0){
                    map_copy[i][j] += map[i][j];
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(0 <= nx && nx < n && 0 <= ny && ny < n){
                            if(map[nx][ny] != 0){
                                int diff = map[i][j] - map[nx][ny];
                                int diff_div = diff / 5;
                                if(diff > 0 && diff_div > 0) {
                                    map_copy[i][j] -= diff_div;
                                    map_copy[nx][ny] += diff_div;

                                }
                            }
                        }
                    }
                }
            }
        }
        map = map_copy;
    }

    public static void build_fish(){
        // step : 회전 단계, col : 현재 열
        // w: 너비, h:높이
        // nw : 다음 너비, nh : 다음 높이
        int step = 0;
        int col = 0;
        int w = 1, h =1;
        int nw = 1, nh = 2;
        while (nw * nh <= n){
            for(int r=n-1;r>n-1-h;r--){
                for(int c=col;c<col+w;c++){
                    int nr = (n-1-w) + (c-col);
                    int nc = (col+w) + (n-1-r);
                    map[nr][nc] = map[r][c];
                    map[r][c] = 0;
                }
            }

            if(step % 2 == 0){
                h++;
                nw++;
            } else {
                w++;
                nh++;
            }
            col += (step/2 +1);
            step++;
        }

    }

    public static void buiid_fish2(){
        int step = n/2;
        for(int c=0;c<step;c++){
            map[n-2][(n-1)-c] = map[n-1][c];
            map[n-1][c] = 0;
        }

        int step_s = step;
        step = step/2;
        for(int c=step_s;c<step_s+step;c++){
            int row = n-3;
            for(int r=n-2;r<n;r++){
                map[row--][n-1-(c-step_s)] = map[r][c];
                map[r][c] = 0;
            }
        }

    }

    public static boolean check(){
        max = 0;
        min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min = Math.min(min, map[n-1][i]);
            max = Math.max(max, map[n-1][i]);

        }
        if(max-min <= k) return true;
        return false;
    }
}
