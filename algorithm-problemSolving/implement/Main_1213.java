// BOJ - 1213 
// 수정중
import java.io.*;
import java.util.Arrays;

public class Main_1213 {
    static String ans;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split("");
        int n = str.length;
        permutation(str, new String[n], new boolean[n], 0, n);

        if(ans == null){
            bw.write(String.valueOf("I'm Sorry Hansoo"));
        } else{
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();

    }

   public static void permutation(String[] str, String[] output,boolean[] visited, int depth, int n){
        if (check){
            return;
       }
        if(depth == n){
            StringBuffer sb = new StringBuffer(String.join("", output));
            if (String.join("", output).equals(sb.reverse().toString()) && check == false) {
                if(!String.join("", output).equals(String.join("", str))) {
                    ans = String.join("", output);
                    check = true;
                    return;
                }
            }
            return;
        }


        for(int i=0;i<n;i++){
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = str[i];
                permutation(str, output,visited, depth+1, n);
                visited[i] = false;
            }
        }
   }

}
