// BOJ - 로봇 청소기(4991번)
// BFS + 순열
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_4991 {
    public static int w, h;
    public static int robot_x, robot_y, dirty_cnt;
    public static boolean[] visited;
    public static int[][][][] bfs_distance;
    public static ArrayList<Node> dirty;
    public static ArrayList<int[]> permu_list;
    public static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static class Node {
        int x;
        int y;
        char what;

        public Node(int x, int y, char what){
            this.x = x;
            this.y = y;
            this.what = what;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true){
            String[] data = br.readLine().split(" ");
            w = Integer.parseInt(data[0]);
            h = Integer.parseInt(data[1]);

            if(w == 0 && h == 0){
                break;
            }
            map = new char[h][w];
            dirty = new ArrayList<>();
            permu_list = new ArrayList<>();
            for(int i=0;i<h;i++){
                String input = br.readLine();
                for(int j=0;j<w;j++){
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == 'o'){
                        robot_x = i;
                        robot_y = j;
                    } else if(map[i][j] == '*'){
                        dirty.add(new Node(i, j, '*'));
                    }
                }
            }
            dirty_cnt = dirty.size();
            int ans = Integer.MAX_VALUE;
            visited = new boolean[dirty_cnt];
            permutation(0, new int[dirty_cnt]);
            bfs_distance = new int[h][w][h][w];
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    bfs(i, j);
                }
            }

            outer:for(int[] p:permu_list){
                int dis_sum = 0;
                int r_x = robot_x;
                int r_y = robot_y;
                for(int idx:p){
                    Node nd = dirty.get(idx);
                    int dis = bfs_distance[r_x][r_y][nd.x][nd.y];
                    if(dis == 0) continue outer;
                    r_x = nd.x;
                    r_y = nd.y;
                    dis_sum += (dis-1);
                }
                ans = Math.min(ans, dis_sum);
            }


            if(ans == Integer.MAX_VALUE){
                bw.write(String.valueOf("-1\n"));

            } else {
                bw.write(String.valueOf(ans+"\n"));
            }

        }
        bw.flush();
        bw.close();

    }
    public static void bfs(int x, int y){

        Queue<Node> q = new LinkedList<>();
        bfs_distance[x][y][x][y] = 1;
        q.add(new Node(x, y, 'o'));

        while (!q.isEmpty()){
            Node nd = q.poll();
            for(int d=0;d<4;d++){
                int nx = nd.x + dx[d];
                int ny = nd.y + dy[d];
                if(0<= nx && nx < h && 0 <= ny && ny < w){
                    if(map[nx][ny] == 'x') continue;
                    if(bfs_distance[x][y][nx][ny] == 0){
                        bfs_distance[x][y][nx][ny] = bfs_distance[x][y][nd.x][nd.y] + 1;
                        q.offer(new Node(nx, ny, map[nx][ny]));
                    }
                }
            }
        }


    }

    public static void permutation(int depth, int[] arr){
        if(depth == dirty_cnt){
            int[] copy = new int[dirty_cnt];
            for(int i=0;i<dirty_cnt;i++){
                copy[i] = arr[i];
            }
            permu_list.add(copy);
        }
        for(int i=0;i<dirty_cnt;i++){
            if(!visited[i]){
                arr[depth] = i;
                visited[i] = true;
                permutation(depth+1, arr);
                visited[i] = false;
            }
        }
    }



}
