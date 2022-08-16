// BOJ - 악덕 영주 혜유(20010번)
// DFS + MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_20010 {
    public static int n, m;
    public static int max, max_node;
    public static int INF = (int)1e9;
    public static class Node implements Comparable<Node>{
        int a;
        int b;
        int dis;
        public Node(int a, int dis){
            this.a = a;
            this.dis = dis;
        }
        public Node(int a, int b, int dis){
            this.a = a;
            this.b = b;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }


    public static int[] parent;
    public static ArrayList<Node>[] list;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];

        ArrayList<Node> edges = new ArrayList<>();
        list = new ArrayList[n];


        for(int i=0;i<n;i++){
            parent[i] = i;
            list[i] = new ArrayList<Node>();
        }


        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Node(a, b, cost));
        }
        Collections.sort(edges);

        int size = edges.size();
        int min_score = 0;

        for(int i=0;i<size;i++){
            Node node = edges.get(i);
            if(find_parent(node.a) != find_parent(node.b)){
                min_score += node.dis;
                list[node.a].add(new Node(node.b, node.dis));
                list[node.b].add(new Node(node.a, node.dis));
                union(node.a, node.b);
            }

        }
        max = Integer.MIN_VALUE;
        visited = new boolean[n];
        dfs(0, 0);
        max = Integer.MIN_VALUE;
        visited = new boolean[n];
        dfs(max_node, 0);

        System.out.println(min_score);
        System.out.println(max);
    }

    public static void dfs(int x, int dis){
        visited[x] = true;

        if(max < dis){
            max = dis;
            max_node = x;
        }

        for(Node node:list[x]){
            if(!visited[node.a]){
                dfs(node.a, dis+node.dis);
            }
        }
    }

    public static int find_parent(int x){
        if(parent[x] == x) return x;
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
