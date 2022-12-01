// 프로그래머스 - 등산 코스 정하기(https://school.programmers.co.kr/learn/courses/30/lessons/118669)
// 다익스트라 + DP
import java.util.*;

class Solution {
    public static class Node {
        public int a;
        public int cost;
        public Node(int a, int cost){
            this.a = a;
            this.cost = cost;
        }
    }
    public static ArrayList<Node>[] list;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        list = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            list[i] = new ArrayList<Node>();
        }
        
        for(int[] path:paths){
            int s = path[0];
            int e = path[1];
            int c = path[2];
            if(isGate(s, gates) || isSummit(e, summits)){
                list[s].add(new Node(e, c));
            } else if(isGate(e, gates) || isSummit(s, summits)){
                list[e].add(new Node(s, c));
            } else {
                list[s].add(new Node(e, c));
                list[e].add(new Node(s, c));
            }
        }
                      
                      
        return dijkstra(n, gates, summits);
    }
                      
    public static int[] dijkstra(int n, int[] gates, int[] summits){
        Queue<Node> q = new LinkedList<>();
        int[] intensity = new int[n+1];
        
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int g:gates){
            intensity[g] = 0;
            q.add(new Node(g, 0));
        }
        
        while(!q.isEmpty()){
            Node node = q.poll();
            
            if(node.cost > intensity[node.a]) continue;
            for(int i=0;i<list[node.a].size();i++){
                Node next = list[node.a].get(i);
                int temp = Math.max(intensity[node.a], next.cost);
                if(intensity[next.a] > temp){
                    intensity[next.a] = temp;
                    q.add(new Node(next.a, temp));
                }
            }   
        }

        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        Arrays.sort(summits);
        for(int s:summits){
            if(intensity[s] < answer[1]){
                answer[0] = s;
                answer[1] = intensity[s];
            }
        }
        
        return answer;
    }
    
    
    public static boolean isGate(int g, int[] gates){
        for(int gate:gates){
            if(g == gate) return true;
        }
        
        return false;
    }
    
    public static boolean isSummit(int s, int[] summits){
        for(int summit:summits){
            if(s == summit) return true;
        }
        return false;
    }
    
}
