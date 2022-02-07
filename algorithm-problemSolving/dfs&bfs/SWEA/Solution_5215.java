// SWEA - 햄버거 다이어트(5215번)
// 조합

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution_5215 {
    public static boolean[] visited;
    public static int max_score;
    public static int n, l;
    public static List<int[]> food;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<=T;test_case++){
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            l = Integer.parseInt(s[1]);
            max_score = 0;
            visited = new boolean[n];
            food = new ArrayList<>();
            for(int i=0;i<n;i++){
                String[] s2 = br.readLine().split(" ");
                int score = Integer.parseInt(s2[0]);
                int cal = Integer.parseInt(s2[1]);
                food.add(new int[]{score, cal});
            }

            for(int i=0;i<=n;i++){
                combination(0, 0, i);
            }

            bw.write(String.valueOf("#"+test_case+" "+max_score));
            bw.write(String.valueOf("\n"));

        }
        bw.flush();
        bw.close();
    }

    public static void combination(int cnt, int start, int r){
        if(cnt == r){
            int score = 0;
            int cal = 0;
            for(int i=0;i<n;i++){
                if(visited[i]){
                    score += food.get(i)[0];
                    cal += food.get(i)[1];
                }
            }
            if(cal <= l){
                max_score = Math.max(max_score, score);
            }
            return;
        }
        for(int i=start;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                combination(cnt+1, i+1, r);
                visited[i] = false;
            }
        }
    }
}
