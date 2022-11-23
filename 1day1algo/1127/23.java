// 프로그래머스 - 단어 변환(43163번)
// DFS
import java.util.*;

class Solution {
    public static boolean[] visited;
    public static String[] word;
    public static int a = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        word = words;
        dfs(begin, target, 0);
        if(a == Integer.MAX_VALUE) return 0;
        return a;
    }
    
    public static void dfs(String w, String target, int cnt){
        if(a < cnt) return;
        if(w.equals(target)){
            a = Math.min(a, cnt);
            return;
        }
        outer:for(int i=0;i<word.length;i++){
            if(visited[i]) continue;
            int no_same = 0;
            for(int j=0;j<w.length();j++){
                if(w.charAt(j) != word[i].charAt(j)){
                    no_same++;     
                }
            }
            if(no_same >= 2){
                continue;
            }
            visited[i] = true;
            dfs(word[i], target, cnt+1);
            visited[i] = false;
            
        }
    }
}
