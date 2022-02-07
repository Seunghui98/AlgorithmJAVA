// BOJ - 탑(2493)
// Stack
import java.io.*;
import java.util.Stack;

public class Solution_2493{
    public static class Top{
        int num;
        int height;
        public Top(int num, int height){
            this.num = num;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        int[] answer = new int[n];
        Stack<Top> stack = new Stack<>();
        for(int i=n-1;i>=0;i--){
            if(stack.isEmpty()){
                stack.push(new Top(i, Integer.parseInt(s[i])));
            } else {
                while (stack.peek().height < Integer.parseInt(s[i])){
                    Top t = stack.pop();
                    answer[t.num] = i+1;
                    if(stack.isEmpty()){
                        break;
                    }

                }
                stack.push(new Top(i, Integer.parseInt(s[i])));
            }
        }

        for(int i=0;i<n;i++){
            bw.write(String.valueOf(answer[i]+" "));
        }
        bw.flush();
        bw.close();
    }
}
