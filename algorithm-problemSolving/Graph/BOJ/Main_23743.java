// BOJ - 방탈출(23743번)
// MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_23743 {
    static int n, m;
    public static int[] parent;
    public static class Edge implements Comparable<Edge>{
        int nodeA;
        int nodeB;
        int cost;
        public Edge(int nodeA, int nodeB, int cost){
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        parent = new int[n+1];


        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            parent[i] = i;
            int ee = Integer.parseInt(st.nextToken());
            edges.add(new Edge(0, i, ee));

        }

        Collections.sort(edges);
        int ans = 0;
        for(Edge e:edges){
            if(find_parent(e.nodeA) != find_parent(e.nodeB)){
                ans += e.cost;
                union(e.nodeA, e.nodeB);
            }
        }
        System.out.println(ans);

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
