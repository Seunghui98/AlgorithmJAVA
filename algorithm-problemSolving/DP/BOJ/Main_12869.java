// BOJ - 뮤탈리스크 (12869번)
// DFS

import java.util.Scanner;

public class Main_12869 {
    public static int n, ans;
    public static int[] scv;
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        scv = new int[3];
        for(int i=0;i<n;i++){
            scv[i] = scan.nextInt();
        }
        ans = 61;
        dfs(0, scv[0], scv[1], scv[2]);
        System.out.println(ans);
    }

    public static void dfs(int depth, int scv1, int scv2, int scv3){
        if(scv1 <= 0 && scv2 <= 0 && scv3 <= 0){
            ans = Math.min(depth, ans);
            return;
        }
        if(ans < depth)
            return;


        if(scv1 >= scv2 && scv1 >= scv3){
            dfs(depth+1, scv1-9, scv2-3, scv3-1);
            dfs(depth+1, scv1-9, scv2-1, scv3-3);
        }

        if(scv2 >= scv1 && scv2 >= scv3){
            dfs(depth+1, scv1-3, scv2-9, scv3-1);
            dfs(depth+1, scv1-1, scv2-9, scv3-3);
        }

        if(scv3 >= scv1 && scv3 >= scv2){
            dfs(depth+1, scv1-1, scv2-3, scv3-9);
            dfs(depth+1, scv1-3, scv2-1, scv3-9);
        }

    }
}
