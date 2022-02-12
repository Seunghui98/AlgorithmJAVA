// BOJ - 숨바꼭질(6118번)
// BFS -> 메모리 조심

import java.io.*;
import java.util.*;

// 2차원 배열 -> 메모리 초과
// ArrayList ?? -> 이걸로..!
// 해쉬맵 ?
public class Main_6118 {
    public static int n, m, max_dis;
    public static boolean[] visited;
    public static ArrayList<Node> ans;
    public static ArrayList<ArrayList<Integer>> list;
    public static class Node implements Comparable<Node>{
        int num;
        int dis;
        public Node(int num, int dis){
            this.num = num;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.num-o.num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        visited = new boolean[n+1];
        list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            String[] data = br.readLine().split(" ");
            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);
            list.get(a).add(b);
            list.get(b).add(a);

        }
        ans = new ArrayList<>();
        max_dis = 0;
        bfs();
        Collections.sort(ans);
        bw.write(String.valueOf(ans.get(0).num+" "+(max_dis)+" "+ans.size()));
        bw.flush();
        bw.close();
    }

    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        visited[1] = true;
        q.offer(new Node(1, 0));
        while (!q.isEmpty()){
            Node now = q.poll();
            if(now.dis > max_dis){
                max_dis = now.dis;
                ans.clear();
                ans.add(now);
            } else if(now.dis == max_dis){
                ans.add(now);
            }

            for(int nx:list.get(now.num)){
                if(!visited[nx]){
                    visited[nx] = true;
                    q.offer(new Node(nx, now.dis+1));
                }
            }

        }
    }
}
