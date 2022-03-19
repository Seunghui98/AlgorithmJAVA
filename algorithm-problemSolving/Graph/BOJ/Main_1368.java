// BOJ - 물대기(1368번)
// MST
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1368 {
    public static int n;

    public static int[] parent;
    public static class Edge implements Comparable<Edge> {
        int nodeA;
        int nodeB;
        int distance;
        public Edge(int nodeA, int nodeB, int distance){
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        parent = new int[n+1];
        int[] cost = new int[n];
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            cost[i] = Integer.parseInt(br.readLine());
            parent[i+1] = i+1;
            edges.add(new Edge(0, i+1, cost[i]));
        }


        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<n;j++){
                int c = Integer.parseInt(st.nextToken());
                if(i != j) edges.add(new Edge(i+1, j+1, c));
            }
        }

        Collections.sort(edges);
        int edge_cost = 0;
        for(Edge e:edges){
            if(find_parent(e.nodeA) != find_parent(e.nodeB)){
                union(e.nodeA, e.nodeB);
                edge_cost += e.distance;
            }
        }
        System.out.println(edge_cost);



    }

    public static int find_parent(int x){
        if(x == parent[x]) return x;
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
