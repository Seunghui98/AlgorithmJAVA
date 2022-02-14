// BOJ - 토마토(7576번)
// BFS

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_7576 {
    public static int[][] map;
    public static int m, n;
    public static Queue<Node> queue;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static class Node{
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
        String[] str = br.readLine().split(" ");
        m = Integer.parseInt(str[0]);
        n = Integer.parseInt(str[1]);
        map = new int[n][m];

        queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            String[] data = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(data[j]);
                if(map[i][j] == 1) queue.add(new Node(i, j));
            }
        }

        int day = 0;
        boolean check = false;
        bfs();
        outer: for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0){
                    check = true;
                    break outer;
                }

                day = Math.max(day, map[i][j]);
            }
        }


        if(check){
            bw.write(String.valueOf("-1"));
        } else{
            bw.write(String.valueOf(day-1));
        }
        bw.flush();
        bw.close();
    }

  public static void bfs(){
        while (!queue.isEmpty()){
            Node node = queue.poll();
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(0<= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 0){
                    map[nx][ny] = map[node.x][node.y] + 1;
                    queue.add(new Node(nx, ny));
                }
            }
        }

  }


}
