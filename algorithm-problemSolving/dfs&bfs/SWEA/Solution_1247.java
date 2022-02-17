// SWEA - 최적경로(1247번)
// DFS(백트래킹)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_1247 {
    public static int n, ans;
    public static int c_x, c_y, h_x, h_y;
    public static boolean[] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static ArrayList<Node> customer;
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
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            n = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(" ");
            c_x = Integer.parseInt(data[0]);
            c_y = Integer.parseInt(data[1]);
            h_x = Integer.parseInt(data[2]);
            h_y = Integer.parseInt(data[3]);
            customer = new ArrayList<>();
            for(int i=0;i<n;i++){
                int x = Integer.parseInt(data[4+2*i]);
                int y = Integer.parseInt(data[4+2*i+1]);
                customer.add(new Node(x, y));
            }

            visited = new boolean[n];
            ans = Integer.MAX_VALUE;
            dfs(c_x, c_y, 0, 0);
            sb.append("#").append(test_case).append(" ");
            sb.append(ans).append("\n");

        }
        System.out.println(sb.toString());
    }

    public static void dfs(int x, int y, int depth, int dis){
        if(depth == n){
            int d = (Math.abs(x-h_x)+Math.abs(y- h_y));
            ans = Math.min(ans, dis+d);
            return;
        }

        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            Node node = customer.get(i);
            int d = (Math.abs(x-node.x)+Math.abs(y- node.y));
            visited[i] = true;
            dfs(node.x, node.y, depth+1, dis+d);
            visited[i] = false;

        }
    }
}
