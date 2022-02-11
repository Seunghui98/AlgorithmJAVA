// SWEA - 정사각형 방(1861번)
// BFS
import java.io.*;

import java.util.LinkedList;

import java.util.Queue;

public class Solution_1861{
    static int n;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for(int i=0;i<n;i++){
                String[] data = br.readLine().split(" ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(data[j]);
                }
            }



            int max_cnt = 0;
            int max_idx = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    int cnt = bfs(i, j);
                    if(max_cnt < cnt){
                        max_cnt = cnt;
                        max_idx = map[i][j];
                    } else if(max_cnt == cnt){
                        if(max_idx > map[i][j]){
                            max_idx = map[i][j];
                        }
                    }
                }
            }

            bw.write(String.valueOf("#"+test_case+" "+max_idx+" "+max_cnt+"\n"));


        }
        bw.flush();
        bw.close();
    }

    public static int bfs(int x, int y){
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()){
            Node node = q.poll();
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(0<= nx && nx < n && 0 <= ny && ny < n){
                    if(!visited[nx][ny] && (map[nx][ny]- map[node.x][node.y] == 1)){
                        cnt++;
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
        return cnt;
    }
}
