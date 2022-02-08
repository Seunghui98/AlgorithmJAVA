// SWEA - 한빈이와 Spot Mart(9229)
// DFS(조합)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_9229 {
    static int n, m, ans;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] str = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            ans = 0;

            String[] data = br.readLine().split(" ");
            arr = new int[n];


            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(data[i]);
            }

            dfs(0, 0, 0);

            if(ans == 0){
                System.out.println("#"+test_case+" -1");
            } else{
                System.out.println("#"+test_case+" "+ans);
            }
        }
    }



    public static void dfs(int depth, int start, int sum){
        if(depth == 2){
            if(sum <= m){
                ans = Math.max(ans, sum);
            }
            return;
        }
        for(int i=start;i<n;i++){
            dfs(depth+1, i+1, sum+arr[i]);
        }
    }

}
