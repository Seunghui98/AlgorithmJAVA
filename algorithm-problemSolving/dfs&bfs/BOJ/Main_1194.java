// BOJ - 달이, 차오른다, 가자. (1194번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194 {
    public static int n, m;
    public static int s_x, s_y;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static class Node{
        int x;
        int y;
        int move;
        int[] key;
        public Node(int x, int y, int move, int[] key) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.key = key;
        }
    }
    public static boolean[][][][][][][][] visited;
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m][2][2][2][2][2][2];
        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                if(map[i][j] == '0'){
                    s_x = i;
                    s_y = j;
                    map[i][j] = '.';
                }
            }
        }

        int ans = bfs(s_x, s_y);
        System.out.println(ans);

    }

    public static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        visited[x][y][0][0][0][0][0][0] = true;
        int[] key = new int[6];
        q.offer(new Node(x, y, 0, key));
        while (!q.isEmpty()){
            Node node = q.poll();
            if(map[node.x][node.y] == '1'){
                return node.move;
            }
            for(int d=0;d<4;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(0 <= nx && nx < n && 0 <= ny && ny < m){
                    if(map[nx][ny] == '#')
                        continue;

                    if(map[nx][ny] == '.' || map[nx][ny] == '1'){
                        int[] k = node.key;
                        if(!visited[nx][ny][k[0]][k[1]][k[2]][k[3]][k[4]][k[5]]){
                            visited[nx][ny][k[0]][k[1]][k[2]][k[3]][k[4]][k[5]] = true;
                            q.offer(new Node(nx, ny, node.move+1, node.key));
                        }

                    } else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                        int[] k_copy = node.key.clone();
                        if(!visited[nx][ny][k_copy[0]][k_copy[1]][k_copy[2]][k_copy[3]][k_copy[4]][k_copy[5]]) {
                            k_copy[map[nx][ny]-'a'] = 1;
                            visited[nx][ny][k_copy[0]][k_copy[1]][k_copy[2]][k_copy[3]][k_copy[4]][k_copy[5]] = true;
                            q.offer(new Node(nx, ny, node.move+1, k_copy));
                        }

                    } else if(map [nx][ny] >= 'A' && map[nx][ny] <= 'F'){
                        int key_idx = map[nx][ny] - 'A';
                        int[] k = node.key;
                        if(node.key[key_idx] == 1 && !visited[nx][ny][k[0]][k[1]][k[2]][k[3]][k[4]][k[5]])  {
                            visited[nx][ny][k[0]][k[1]][k[2]][k[3]][k[4]][k[5]] = true;
                            q.offer(new Node(nx , ny, node.move+1, node.key));
                        }

                    }
                }
            }
        }

        return -1;
    }
}
