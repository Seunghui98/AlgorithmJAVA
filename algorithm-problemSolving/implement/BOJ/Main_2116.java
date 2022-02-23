// BOJ - 주사위 쌓기(2116번)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2116 {
    public static int[][] dice;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dice = new int[n][6];
        StringTokenizer st = null;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<6;j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int start=1;start<=6;start++){
            int sum = 0;
            int up_index = find_index(dice, 0, start);
            int down_index = find_up(up_index);
            int max_value = find_max(up_index, down_index, 0);
            sum += max_value;

            int up_value = start;
            for(int i=1;i<n;i++){
                down_index = find_index(dice, i, up_value);
                up_index = find_up(down_index);
                up_value = dice[i][up_index];
                max_value = find_max(up_index, down_index, i);
                sum += max_value;
            }

            ans = Math.max(sum, ans);
        }

        System.out.println(ans);


    }

    public static int find_max(int up_idx, int down_idx, int row){
        int max = 0;
        for(int i=0;i<6;i++){
            if(i == up_idx || i == down_idx) continue;
            max = Math.max(max, dice[row][i]);
        }
        return max;
    }

    public static int find_index(int[][] arr, int row, int find){
        for(int i=0;i<6;i++){
            if(arr[row][i] == find){
                return i;
            }
        }
        return 0;
    }

    public static int find_up(int down_index){
        if(down_index == 0){
            return 5;
        } else if(down_index ==1){
            return 3;
        } else if(down_index==2){
            return 4;
        } else if (down_index ==3){
            return 1;
        }else if(down_index ==4){
            return 2;
        } else {
            return 0;
        }
    }


}
