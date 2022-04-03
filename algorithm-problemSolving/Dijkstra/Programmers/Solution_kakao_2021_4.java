// 카카오 2021 4번
// 다익스트라 -> 푸는 중..
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution_kakao_2021_4 {
    public static void main(String[] args){

    }
    public static int INF = (int)1e9;
    public static int[] distance;
    public static ArrayList<ArrayList<Node>> list1;
    public static ArrayList<ArrayList<Node>> list2;
    public static class Node implements Comparable<Node> {
        int index;
        int distance;
        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance-o.distance;
        }
    }

    public static HashMap<Integer, Integer> hmap;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        hmap = new HashMap<Integer, Integer>();
        for(int t=0;t<traps.length;t++){
            hmap.put(traps[t], 0);
        }
        distance = new int[n+1];
        Arrays.fill(distance, INF);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list1.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }
        for(int i=0;i<roads.length;i++){
            int a = roads[i][0];
            int b = roads[i][1];
            int c = roads[i][2];
            list1.get(a).add(new Node(b, c));
            list2.get(b).add(new Node(a, c));
        }
        dijkstra(start);
        for(int d:distance){
            System.out.print(d+" ");
        }
        System.out.println();
        answer = distance[end];
        return answer;

    }

    public static void dijkstra(int start){
        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()){
            Node node = pq.poll();
            if(hmap.containsKey(node.index)){
                // 정방향 -> 역방향
                if(hmap.get(node.index) == 1){
                    hmap.put(node.index, -1);
                    for(int i=0;i<list2.get(node.index).size();i++){
                        int index = list2.get(node.index).get(i).index;
                        if(hmap.containsKey(index)){
                            int dir = hmap.put(index, -hmap.get(index));
                        }

                        int cost = node.distance + list2.get(node.index).get(i).distance;
                        if(cost < distance[list2.get(node.index).get(i).index]){
                            distance[list2.get(node.index).get(i).index] = cost;
                        }
                        pq.offer(new Node(list2.get(node.index).get(i).index, cost));
                    }
                } else { // 역방향 -> 정방향
                    hmap.put(node.index, 1);
                    for(int i=0;i<list1.get(node.index).size();i++){
                        int index = list1.get(node.index).get(i).index;
                        if(hmap.containsKey(index)){
                            int dir = hmap.put(index, -hmap.get(index));
                        }
                        int cost = node.distance + list1.get(node.index).get(i).distance;
                        if(cost < distance[list1.get(node.index).get(i).index]){
                            distance[list1.get(node.index).get(i).index] = cost;
                        }
                        pq.offer(new Node(list1.get(node.index).get(i).index, cost));
                    }
                }
            } else {
                if (distance[node.index] < node.distance) continue;
                for(int i=0;i<list1.get(node.index).size();i++){
                    int cost = node.distance + list1.get(node.index).get(i).distance;
                    if(cost < distance[list1.get(node.index).get(i).index]){
                        distance[list1.get(node.index).get(i).index] = cost;
                        pq.offer(new Node(list1.get(node.index).get(i).index, cost));
                    }
                }
            }
        }
    }
}
