// 프로그래머스 - N으로 표현(https://school.programmers.co.kr/learn/courses/30/lessons/42895)
// DFS
class Solution {
    public static int n;
    public static int target;
    public static int answer = Integer.MAX_VALUE;
    
    public int solution(int N, int number) {
        n = N;
        target = number;
        dfs(0, 0);
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
    
    public static void dfs(int cnt, int num){
        if(cnt > 8){
            return;
        }
        
        if(num == target){
            answer = Math.min(answer, cnt);
            return;
        }
        
        int temp_N = n;
        for(int i=0;i<8-cnt;i++){
            dfs(cnt+i+1, num + temp_N);
            dfs(cnt+i+1, num - temp_N);
            dfs(cnt+i+1, num * temp_N);
            dfs(cnt+i+1, num / temp_N);
            temp_N = temp_N * 10 + n;
        }
    }
    
}
