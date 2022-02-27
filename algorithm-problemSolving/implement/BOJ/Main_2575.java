// BOJ - 빙고(2575번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2578 {
    public static int[][] map;
    public static boolean[][] bingo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        bingo = new boolean[5][5];
        StringTokenizer st = null;
        for(int i=0;i<5;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<5;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        outer: for(int i=0;i<5;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<5;j++){
                int num = Integer.parseInt(st.nextToken());
                int[] arr = find(num);
                bingo[arr[0]][arr[1]] = true;
                if(check()){
                  ans = 5*i + (j+1);
                  break outer;
                }
            }
        }
        System.out.println(ans);
    }

    public static int[] find(int num){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(map[i][j] == num){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static boolean check(){
        int bingo_cnt = 0;
        // 가로
        for(int i=0;i<5;i++){
            int cnt = 0;
            for(int j=0;j<5;j++){
                if(bingo[i][j]) cnt++;
            }
            if(cnt == 5) bingo_cnt++;
        }
        // 세로
        for(int i=0;i<5;i++){
            int cnt = 0;
            for(int j=0;j<5;j++){
                if(bingo[j][i]) cnt++;
            }
            if(cnt == 5) bingo_cnt++;
        }
        // 대각선 (위->아래)
        int cnt = 0;
        for(int i=0;i<5;i++){
            if(bingo[i][i]) cnt++;
        }
        if(cnt == 5) bingo_cnt++;
        cnt = 0;
        // 대각선(아래 -> 위)
        for(int i=0;i<5;i++){
            if(bingo[4-i][i]) cnt++;
        }
        if(cnt == 5) bingo_cnt++;

        if(bingo_cnt >= 3) return true;
        return false;
    }
}
