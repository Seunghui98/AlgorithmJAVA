// BOJ - 참외밭(2477번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[6][2];
        int w_max = 0;
        int h_max = 0;
        int w_max_idx = 0;
        int h_max_idx = 0;
        for(int i=0;i<6;i++){
            String[] data = br.readLine().split(" ");
            map[i][0] = Integer.parseInt(data[0]);
            map[i][1] = Integer.parseInt(data[1]);
            if(map[i][0] == 1 || map[i][0] == 2){
                if(map[i][1] > w_max){
                    w_max = map[i][1];
                    w_max_idx = i;
                }
            } else {
                if(map[i][1] > h_max){
                    h_max = map[i][1];
                    h_max_idx = i;
                }
            }
        }

        int w = 0;
        int h = 0;
        if(w_max_idx == 0) h = Math.abs(map[5][1] - map[1][1]);
        else if(w_max_idx == 5) h = Math.abs(map[4][1] - map[0][1]);
        else h = Math.abs(map[w_max_idx+1][1] - map[w_max_idx-1][1]);

        if(h_max_idx == 0) w = Math.abs(map[5][1] - map[1][1]);
        else if(h_max_idx == 5) w = Math.abs(map[4][1] - map[0][1]);
        else w = Math.abs(map[h_max_idx+1][1] - map[h_max_idx-1][1]);

        System.out.println((w_max*h_max - w*h) * n);
    }
}
