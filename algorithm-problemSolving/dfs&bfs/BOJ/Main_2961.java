// BOJ - 도영이가 만든 맛있는 음식(2961)
// 재귀 - 부분집합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2961 {
    public static int[][] food;
    public static boolean[] visited;
    public static int n, diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        food = new int[n][2];

        for(int i=0;i<n;i++){
            String[] data = br.readLine().split(" ");
            food[i][0] = Integer.parseInt(data[0]);
            food[i][1] = Integer.parseInt(data[1]);
        }
        diff = Integer.MAX_VALUE;
        visited = new boolean[n];
        generateSubset(0);

        System.out.println(diff);
    }

    public static void generateSubset(int cnt){
        if(cnt == n){
            int sum1 = 0;
            int sum2 = 0;
            for(int i=0;i<n;i++){
                if(visited[i]){
                    if(sum1 == 0 || sum2 == 0){
                        sum1 += food[i][0];
                        sum2 += food[i][1];
                    } else {
                        sum1 *= food[i][0];
                        sum2 += food[i][1];
                    }
                }

            }

            if(sum1 != 0 || sum2 != 0) {
                diff = Math.min(Math.abs(sum1 - sum2), diff);
            }
            return;
        }

        visited[cnt] = true;
        generateSubset(cnt+1);
        visited[cnt] = false;
        generateSubset(cnt+1);
    }
}
