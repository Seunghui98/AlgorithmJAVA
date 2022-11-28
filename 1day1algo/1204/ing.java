import java.util.*;

class Solution {
    public static int cnt;
    public static boolean check = false;
    public static String[] a;
    public static boolean[] visited;
    
    public static class Node implements Comparable<Node> {
        String start;
        String end;
        public Node(String start, String end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Node o){
            if(this.start.equals(o.start)){
                char[] charArray1 = this.end.toCharArray();
                char[] charArray2 = o.end.toCharArray();
            
            for(int i=0;i<3;i++){
                if(charArray1[i] == charArray2[i]) continue;
                return charArray1[i] - charArray2[i];
            }
            }
            return 0;
        }
    }
    public static ArrayList<Node> list = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        cnt = 0;
        HashMap<String, Integer> hash = new HashMap<>();
        for(String[] t:tickets){
            list.add(new Node(t[0], t[1]));
            if(!hash.containsKey(t[0])) {
                hash.put(t[0], 1);
                cnt++;
            }
            
            if(!hash.containsKey(t[1])) {
                hash.put(t[1], 1);
                cnt++;
            }
        }
 
        Collections.sort(list);
        visited = new boolean[list.size()+1];
        a = new String[list.size()+1];
        a[0] = "ICN";
        visited[0] = true;
        dfs(list.get(0).start, list.get(0).end, 0);
        return a;
    }
    
    
    public static void dfs(String start, String end, int depth){
        System.out.println(start+" "+end+" !");
        a[depth] = start;
        a[depth+1] = end;
        if(check) return;
        if(depth == list.size()-1){
            System.out.println(start+" "+end+" &&");
            check = true;
            return;
        }
        for(int i=0;i<list.size();i++){
            if(!visited[i]){
                if(list.get(i).start.equals(end)){
                System.out.println(end+" "+list.get(i).start);
                visited[depth] = true;
                dfs(list.get(i).start, list.get(i).end, depth+1);
                visited[depth] = false;
                }
            }
        }
    }

}
