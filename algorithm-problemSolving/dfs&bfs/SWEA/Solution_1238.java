// SWEA - Contact(1238번)
// BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_1238 {
    static int n, m, start, ans;
    static LinkedList<LinkedList<Integer>> list;
    public static class Node {
        int num;
        int depth;
        public Node(int num, int depth){
            this.num = num;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 10;
        for(int test_case=1;test_case<=T;test_case++){
            String[] data = br.readLine().split(" ");
            n = 100;
            list = new LinkedList<>();
            for(int i=0;i<=100;i++){
                list.add(new LinkedList<>());
            }

            m = Integer.parseInt(data[0]);
            start = Integer.parseInt(data[1]);
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<m/2;i++){
                list.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
            }
            ans = bfs();
            sb.append("#").append(test_case).append(" ").append(ans).append("\n");

        }

        System.out.println(sb.toString());
    }

    public static int bfs(){
        boolean[] visited = new boolean[101];
        Queue<Node> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(new Node(start, 0));
        ArrayList<Integer> depth_list = new ArrayList<>();
        int max_depth = 0;
        depth_list.add(start);
        while (!queue.isEmpty()){
            Node now = queue.poll();
            if(max_depth < now.depth){
                max_depth = now.depth;
                depth_list.clear();
                depth_list.add(now.num);
            } else if (max_depth == now.depth){
                depth_list.add(now.num);
            }

            for(Integer adj:list.get(now.num)){
                if(!visited[adj]){
                    visited[adj] = true;
                    queue.offer(new Node(adj, now.depth+1));
                }
            }
        }

        Collections.sort(depth_list);
        return depth_list.get(depth_list.size()-1);
    }
}
