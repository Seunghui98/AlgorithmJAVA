// BOJ - 최단 경로(1753번)
// Dijkstra

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1753 {
    public static class Node{
        int x;
        int dis;
        public Node(int x, int dis){
            this.x = x;
            this.dis = dis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[] min_dis = new int[v+1];
        int start = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for(int i=0;i<v+1;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
        }


        boolean[] visited = new boolean[v+1];

        Arrays.fill(min_dis, Integer.MAX_VALUE);
        min_dis[start] = 0;
        for(int i=1;i<=v;i++){
            int min = Integer.MAX_VALUE, current = 0;
            for(int j=1;j<=v;j++){
                if(!visited[j] && min > min_dis[j]){
                    min = min_dis[j];
                    current = j;
                }
            }

            visited[current] = true;


                    for(Node nd:list.get(current)){
                        if(!visited[nd.x]) {
                            if (min_dis[nd.x] > min_dis[current] + nd.dis) {
                                min_dis[nd.x] = min_dis[current] + nd.dis;

                            }
                        }


            }
        }


    for(int i=1;i<v+1;i++){
        if(min_dis[i] == Integer.MAX_VALUE){
            System.out.println("INF");
        } else {
            System.out.println(min_dis[i]);
        }
    }

    }
}
