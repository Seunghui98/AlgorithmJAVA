// BOJ - 말이 되고픈 원숭이(1600번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600 {
    public static int[] dx = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] dy = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2};
    public static int w, h, k;
    public static int[][] map;
    public static class Node {
        int x;
        int y;
        int move;
        int use;
        public Node(int x, int y, int move, int use){
            this.x = x;
            this.y = y;
            this.move = move;
            this.use = use;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for(int i=0;i<h;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<w;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs();
        System.out.println(ans);
    }

    public static int bfs(){
        boolean[][][] visited = new boolean[h][w][k+1];
        visited[0][0][0] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 0));

        while (!q.isEmpty()){
            Node node = q.poll();
            if(node.x == (h-1) && node.y == (w-1)){
                return node.move;
            }
            for(int d=0;d<12;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(0 <= nx && nx < h && 0 <= ny && ny < w){
                    if(d<4){
                        if(!visited[nx][ny][node.use] && map[nx][ny] != 1){
                            visited[nx][ny][node.use] = true;
                            q.add(new Node(nx, ny, node.move+1, node.use));
                        }
                    } else {
                        if(node.use+1 <= k && !visited[nx][ny][node.use+1] && map[nx][ny] != 1){
                            visited[nx][ny][node.use+1] = true;
                            q.add(new Node(nx, ny, node.move+1, node.use+1));
                        }
                    }
                }
            }

        }

        return -1;
    }
}
