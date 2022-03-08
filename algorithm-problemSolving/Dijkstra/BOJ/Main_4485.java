// BOJ - 녹색 옷 입은 애가 젤다지?(4485번)
// 다익스트라
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485 {
    public static int n;
    public static int[] dx ={-1, 1, 0, 0};
    public static int[] dy ={0, 0, -1, 1};
    public static int[][] map;
    public static int[][] distance;
    public static boolean[][] visited;
    public static final int INF = (int)1e9;
    public static class Node implements Comparable<Node>{
        int x;
        int y;
        int distance;
        public Node(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance-o.distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while (true){
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            map = new int[n][n];
            distance = new int[n][n];
            visited = new boolean[n][n];
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = INF;
                }
            }

            dijkstra();
            sb.append("Problem ").append(cnt).append(": ").append(distance[n-1][n-1]).append("\n");
            cnt++;


        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(){
        visited[0][0] = true;
        distance[0][0] = map[0][0];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, distance[0][0]));
        while (!pq.isEmpty()){
            Node node = pq.poll();
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(0<= nx && nx < n && 0 <= ny && ny < n){
                    if(!visited[nx][ny]){
                        int cost = node.distance + map[nx][ny];
                        if(cost < distance[nx][ny]){
                            distance[nx][ny] = cost;
                            visited[nx][ny] = true;
                            pq.offer(new Node(nx, ny, cost));
                        }
                    }
                }
            }
        }
    }
}
