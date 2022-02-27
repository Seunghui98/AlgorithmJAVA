// BOJ - 직사각형(2527번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<4;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x_1 = Integer.parseInt(st.nextToken());
            int y_1 = Integer.parseInt(st.nextToken());
            int p_1 = Integer.parseInt(st.nextToken());
            int q_1 = Integer.parseInt(st.nextToken());

            int x_2 = Integer.parseInt(st.nextToken());
            int y_2 = Integer.parseInt(st.nextToken());
            int p_2 = Integer.parseInt(st.nextToken());
            int q_2 = Integer.parseInt(st.nextToken());

            // 닿지 않음
            if(p_1 < x_2 || p_2 < x_1 || q_1 < y_2 || y_1 > q_2){
                System.out.println("d");
            }
            // 점
            else if((x_1 == p_2 && q_1 == y_2) || (p_1 == x_2 && q_1 == y_2) || (x_1 == p_2 && y_1 == q_2) || (p_1 == x_2 && y_1 == q_2)){
               System.out.println("c");
            }
            // 선
            else if((p_1 == x_2) || (x_1 == p_2) || (q_1 == y_2) || (y_1 == q_2)){
                System.out.println("b");
            }
            // 직사각형
            else {
                System.out.println("a");
            }

        }
    }
}
