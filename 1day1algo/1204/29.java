// 프로그래머스 - 여행경로(https://school.programmers.co.kr/learn/courses/30/lessons/43164)
// DFS

import java.util.*;

class Solution {
    public static int cnt;
    public static ArrayList<String> list = new ArrayList<>();
    public static boolean[] visited;
    public static String[][] ticket;
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        ticket = tickets;
        dfs("ICN", "ICN", 0);
        
        Collections.sort(list);
        return list.get(0).split(" ");
    }
    
    
    public static void dfs(String t, String str, int depth){ 
       if(depth == ticket.length){
           list.add(str);
           return;
       }
       for(int i=0;i<ticket.length;i++){
           if(!visited[i] && ticket[i][0].equals(t)){
               visited[i] = true;
               dfs(ticket[i][1], str+" "+ticket[i][1], depth+1);
               visited[i] = false;
           }
       } 
        
    }

}
