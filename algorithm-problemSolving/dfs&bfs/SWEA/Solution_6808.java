// SWEA - 규영이와 인영이의 카드게임(6808번)
// DFS(조합)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_6808 {
    public static int[] arr;
    public static int win_Cnt, lose_Cnt;
    public static boolean[] card;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] data = br.readLine().split(" ");
            arr = new int[9];
            card = new boolean[19];
            visited = new boolean[19];
            for(int i=0;i<9;i++){
                arr[i] = Integer.parseInt(data[i]);
                card[arr[i]] = true;
            }
            win_Cnt = 0;
            lose_Cnt = 0;
            permuation(0, new int[9]);
            System.out.println("#"+test_case+" "+win_Cnt+" "+lose_Cnt);

        }


    }

    public static void permuation(int depth, int[] pick){
        if(depth==9){
            if(game(pick) == 1){
                win_Cnt++;
            } else if(game(pick) == -1){
                lose_Cnt++;
            }
            return;
        }
        for(int i=1;i<=18;i++){
            if(!visited[i] && !card[i]){
                pick[depth] = i;
                visited[i] = true;
                permuation(depth+1, pick);
                visited[i] = false;
            }
        }
    }

    public static int game(int[] pick){
        int gu_sum = 0;
        int in_sum = 0;
        for(int i=0;i<9;i++){
            if(pick[i] > arr[i]){
                in_sum += (arr[i]+pick[i]);
            } else if(pick[i] < arr[i]){
                gu_sum += (arr[i]+pick[i]);
            }
        }

        if(gu_sum > in_sum){
            return 1;
        } else if(gu_sum < in_sum){
            return -1;
        }
        return 0;
    }
}
