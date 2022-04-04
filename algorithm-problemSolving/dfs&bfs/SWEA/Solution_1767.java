// SWEA - 프로세서 연결하기(1767번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1767 {
    public static int[] dx = {0, -1, 1, 0, 0};
    public static int[] dy = {0, 0, 0, -1, 1};
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
            boolean[][] visited = new boolean[n][n];

            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1){
                        if(i == 0 || j == 0 || i == n-1 || j == n-1){
                            visited[i][j] = true;
                            cnt++;
                        } else {
                            cores.add(new Node(i , j));
                        }
                    }
                }
            }
            max_cnt = 0;
            min_ans = Integer.MAX_VALUE;
            int core_len = cores.size();
            dfs(visited, 0, core_len, 0, 0);
            if(min_ans == Integer.MAX_VALUE){
                sb.append("#"+test_case+" ").append(0).append("\n");
            } else {
                sb.append("#"+test_case+" ").append(min_ans).append("\n");
            }

        }
        System.out.println(sb.toString());
    }

    public static void dfs(boolean[][] visited, int depth, int core_len, int c, int l){

        if(depth ==core_len){

            if(c > max_cnt){
                max_cnt = c;
                min_ans = l;
            } else if(c == max_cnt){
                min_ans = Math.min(min_ans, l);
            }
            return;
        }
        for(int d=0;d<5;d++){
            if(d==0){
                dfs(visited, depth+1, core_len, c, l);
            } else {
                int nx = cores.get(depth).x + dx[d];
                int ny = cores.get(depth).y + dy[d];
                if(visited[nx][ny] || map[nx][ny] == 1){
                    continue;
                }

                boolean[][] visited_copy = new boolean[n][n];
                for(int i=0;i<n;i++){
                    visited_copy[i] = visited[i].clone();
                }

                int len_cnt = check(visited_copy, d, nx, ny);

                if(len_cnt != -1){
                    visited_copy[cores.get(depth).x][cores.get(depth).y] = true;
                    dfs(visited_copy, depth+1, core_len, c+1, l+len_cnt);
                }
            }
        }

    }

    public static int check(boolean[][] visited, int dir, int nx, int ny){
        Queue<Node> q = new LinkedList<>();
        int len_cnt = 0;
        while (true){
            if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                break;
            }
            if(visited[nx][ny] || map[nx][ny] == 1){
                return -1;
            }
            q.offer(new Node(nx, ny));
            len_cnt++;
            nx += dx[dir];
            ny += dy[dir];
        }
        while (!q.isEmpty()){
            Node nd = q.poll();
            visited[nd.x][nd.y] = true;
        }


        return len_cnt;
    }


}
