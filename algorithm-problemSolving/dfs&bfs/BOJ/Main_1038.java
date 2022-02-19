// BOJ - 감소하는 수(1038번)
// DFS(백트래킹)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static int n, cnt;
    public static boolean check;
    public static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        cnt = -1;

        for(int i=1;i<=10;i++){
            map = new int[i];
            dfs(0, i);
            if(check){
                break;
            }
        }


        if(!check){
            System.out.println(-1);
        }

    }

    public static void dfs(int index, int size){
        if(check){
            return;
        }

        for(int i=0;i<index-1;i++){
            if(map[i] <= map[i+1]){
                return;
            }
        }

        if(index == size){
            cnt++;
            if(cnt == n){
                for(int i=0;i<size;i++){
                    System.out.print(map[i]);
                }
                System.out.println();
                check = true;
            }
            return;
        }

        for(int i=0;i<=9;i++){
            map[index] = i;
            dfs(index+1, size);
        }



    }


}
