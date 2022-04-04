// SWEA - 프로세서 연결하기 - 풀이2
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767 {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int n, max_cnt, min_ans, cnt;
    public static int[][] map;
    public static ArrayList<Node> cores;
    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            n = Integer.parseInt(br.readLine());
            cores = new ArrayList<>();
            map = new int[n][n];

            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1){
                        if(i == 0 || j == 0 || i == n-1 || j == n-1){
                            continue;
                        } else {
                            cores.add(new Node(i , j));
                        }
                    }
                }
            }
            max_cnt = 0;
            min_ans = Integer.MAX_VALUE;
            int core_len = cores.size();
            dfs(0, core_len, 0, 0);
            if(min_ans == Integer.MAX_VALUE){
                sb.append("#"+test_case+" ").append(0).append("\n");
            } else {
                sb.append("#"+test_case+" ").append(min_ans).append("\n");
            }

        }
        System.out.println(sb.toString());
    }

    public static void dfs(int depth, int core_len, int c, int l){

        if(depth ==core_len){

            if(c > max_cnt){
                max_cnt = c;
                min_ans = l;
            } else if(c == max_cnt){
                min_ans = Math.min(min_ans, l);
            }
            return;
        }
        for(int d=0;d<4;d++){
            int nx = cores.get(depth).x + dx[d];
            int ny = cores.get(depth).y + dy[d];
            int len_cnt = check(d, nx, ny);

            if(len_cnt == 0) {
                dfs(depth + 1, core_len, c , l);
            } else {
                nx = cores.get(depth).x + dx[d];
                ny = cores.get(depth).y + dy[d];
                for(int i=0;i<len_cnt;i++){
                    map[nx][ny] = 1;
                    nx += dx[d];
                    ny += dy[d];
                }
                dfs(depth + 1, core_len, c + 1, l + len_cnt);
                nx = cores.get(depth).x + dx[d];
                ny = cores.get(depth).y + dy[d];
                for(int i=0;i<len_cnt;i++){
                    map[nx][ny] = 0;
                    nx += dx[d];
                    ny += dy[d];
                }
            }

        }

    }

    public static int check(int dir, int x, int y){
        int nx = x;
        int ny = y;
        int len_cnt = 0;
        while (true){
            if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                break;
            }
            if(map[nx][ny] == 1){
                return 0;
            }
            len_cnt++;
            nx += dx[dir];
            ny += dy[dir];
        }
        return len_cnt;
    }


}
