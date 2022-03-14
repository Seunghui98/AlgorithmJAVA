// BOJ - 괄호 추가하기(16637번)
// DFS(백트래킹)
// 삼성 A형 기출
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main_16637 {
    public static int n;

    public static ArrayList<Integer> number;
    public static ArrayList<Character> cal;

    public static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        char[]input = br.readLine().toCharArray();

        cal = new ArrayList<>();
        number = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(input[i] == '*' || input[i] == '+' || input[i] == '-'){
                cal.add(input[i]);
            } else {
                number.add(input[i] - '0');
            }
        }
        ans = Integer.MIN_VALUE;
        dfs(number.get(0), 0);




        System.out.println(ans);
    }

    public static void dfs(int result, int idx){
       if(idx == cal.size()){
           ans = Math.max(ans, result);
           return;
       }
       dfs(cal(result, number.get(idx+1), cal.get(idx)), idx+1);

       if(idx+2 <= cal.size()){
           dfs(cal(result, cal(number.get(idx+1), number.get(idx+2),cal.get(idx+1)), cal.get(idx)),idx+2);

       }
    }



    public static int cal(int a, int b, char cal_str){
        if(cal_str == '*'){
            return a*b;
        } else if(cal_str == '-'){
            return a-b;
        } else {
            return a+b;
        }
    }
}
