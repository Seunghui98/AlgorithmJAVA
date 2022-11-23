import java.util.*;

class Solution {
    public static boolean[] visited;
    public static String[] word;
    public static int a = 0;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        word = words;
        dfs(begin, target, 0);
        return answer;
    }
    
    public static void dfs(String w, String target, int cnt){
        if(a != 0) return;
        if(w.equals(target)){
            a = cnt;
            return;
        }
        outer:for(int i=0;i<word.length;i++){
            if(visited[i]) continue;
            int no_same = 0;
            for(int j=0;j<w.length();j++){
                if(no_same >= 2){
                    continue outer;
                }
                 
                if(w.charAt(j) != word[i].charAt(j)){
                    no_same++;     
                }
            }
            System.out.println(word[i]);
            visited[i] = true;
            dfs(word[i], target, cnt+1);
            visited[i] = false;
            
        }
    }
}
