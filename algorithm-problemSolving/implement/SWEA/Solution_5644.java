// SWEA - 무선충전(5466번)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Solution_5644 {
    public static class Charger{
        int x;
        int y;
        int c;
        int p;
        public Charger(int x, int y, int c, int p){
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }


    }
    public static int[] dx = {0, -1, 0, 1, 0};
    public static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case <= T;test_case++){
            String[] data = br.readLine().split(" ");
            int m = Integer.parseInt(data[0]);
            int a = Integer.parseInt(data[1]);

            int[] move_a = new int[m];
            int[] move_b = new int[m];
            String[] data2 = br.readLine().split(" ");
            String[] data3 = br.readLine().split(" ");
            for(int i=0;i<m;i++){
                move_a[i] = Integer.parseInt(data2[i]);
                move_b[i] = Integer.parseInt(data3[i]);
            }



            ArrayList<Charger> c_list = new ArrayList<>();
            int a_x = 1;
            int a_y = 1;
            int b_x = 10;
            int b_y = 10;
            int ans = 0;

            for(int i=0;i<a;i++){
                String[] input = br.readLine().split(" ");
                int y = Integer.parseInt(input[0]);
                int x = Integer.parseInt(input[1]);
                int c = Integer.parseInt(input[2]);
                int p = Integer.parseInt(input[3]);

                c_list.add(new Charger(x, y, c, p));
            }




            for(int i=-1;i<m;i++){
                if(i != -1) {
                    a_x += dx[move_a[i]];
                    a_y += dy[move_a[i]];
                    b_x += dx[move_b[i]];
                    b_y += dy[move_b[i]];
                }
                int[][] use = new int[2][a];
                for(int j=0;j<a;j++){
                    Charger cr = c_list.get(j);
                    if((Math.abs(cr.x-a_x)+ Math.abs(cr.y-a_y)) <= cr.c){
                        use[0][j] += 1;
                    }

                    if((Math.abs(cr.x-b_x)+ Math.abs(cr.y-b_y)) <= cr.c){
                        use[1][j] += 1;
                    }
                }
                int sum = 0;
                for(int p=0;p<a;p++){
                    for(int q=0;q<a;q++){
                        int temp_sum = 0;
                        if(use[0][p] >= 1){
                            if(use[1][q] >= 1){
                                if(p == q){
                                    temp_sum = c_list.get(p).p;
                                } else {
                                    temp_sum = (c_list.get(p).p + c_list.get(q).p);
                                }
                            } else {
                                temp_sum = c_list.get(p).p;
                            }
                        } else {
                            if(use[1][q] >= 1){
                                temp_sum = c_list.get(q).p;
                            }
                        }

                        sum = Math.max(temp_sum, sum);
                    }


                }
                ans += sum;


            }

            System.out.println("#"+test_case+" "+ans);

        }
    }
}
