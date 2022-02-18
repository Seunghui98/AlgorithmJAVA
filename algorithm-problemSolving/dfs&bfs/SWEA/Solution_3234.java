// SWEA - 준환이의 양팔저울(3234번)
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_3234 {
    public static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case <= T;test_case++){
            int n = Integer.parseInt(br.readLine());
            int[] weight = new int[n];
            String[] data = br.readLine().split(" ");
            for(int i=0;i<n;i++){
                weight[i] = Integer.parseInt(data[i]);
            }
            ans = 0;
            boolean[] visited = new boolean[n+1];
            // left
            Set(weight, visited, n, 0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());

    }

    public static void Set(int[] weight, boolean[] visited, int n, int depth, int sum_l, int sum_r){
        if(depth == n){
            ans++;
            return;
        }

        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            visited[i] = true;
            Set(weight, visited, n, depth+1, sum_l+weight[i], sum_r);

            if(weight[i]+sum_r <= sum_l){
                Set(weight, visited, n, depth+1, sum_l, sum_r+weight[i]);
            }
            visited[i] = false;
        }


    }



}
