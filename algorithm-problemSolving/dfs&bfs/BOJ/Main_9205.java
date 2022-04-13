// BOJ - 맥주 마시면서 걸어가기 (9205번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9205 {
    public static ArrayList<Node> list = new ArrayList<>();
    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int s_x, s_y, e_x, e_y, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            n = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for(int i=0;i<n+2;i++){
                st = new StringTokenizer(br.readLine(), " ");
                if(i==0){
                    s_x = Integer.parseInt(st.nextToken());
                    s_y = Integer.parseInt(st.nextToken());
                } else if(i==n+1){
                    e_x = Integer.parseInt(st.nextToken());
                    e_y = Integer.parseInt(st.nextToken());
                } else {
                    list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                }
            }
            boolean result = bfs();
            sb.append(result?"happy":"sad").append("\n");


        }
        System.out.println(sb.toString());
    }

    public static boolean bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(new Node(s_x, s_y));
        while (!q.isEmpty()){
            Node node = q.poll();

            if(Math.abs(node.x-e_x) + Math.abs(node.y-e_y) <= 1000 ){
                return true;
            }
            for(int i=0;i<n;i++){
                if(!visited[i]){
                    Node nt_Node = list.get(i);
                    if(Math.abs(node.x-nt_Node.x) + Math.abs(node.y- nt_Node.y) <= 1000){
                        visited[i] = true;
                        q.offer(new Node(nt_Node.x, nt_Node.y));
                    }
                }
            }
        }
        return false;

    }
}
