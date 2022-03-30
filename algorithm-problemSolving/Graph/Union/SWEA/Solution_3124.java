// SWEA - 최소 스패닝 트리(3124번)
// MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_3124 {
    public static int[] parent;
    public static class Edge implements Comparable<Edge>{
        int a;
        int b;
        int dis;
        public Edge(int a, int b, int dis){
            this.a = a;
            this.b = b;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dis - o.dis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            parent = new int[v];
            for(int i=0;i<v;i++){
                parent[i] = i;
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            for(int i=0;i<e;i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pq.add(new Edge(a-1, b-1, c));
            }

            long cost = 0;
            while (!pq.isEmpty()){
                Edge edge = pq.poll();
                if(find_parent(edge.a) != find_parent(edge.b)){
                    cost += edge.dis;
                    union(edge.a, edge.b);
                }
            }

            sb.append("#"+test_case+" ").append(cost).append("\n");

        }
        System.out.println(sb.toString());
    }

    public static int find_parent(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find_parent(parent[x]);
    }

    public static void union(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        if(a < b){
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
