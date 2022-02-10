// BOJ - 색종이 접기(2563번)
// 구현
import java.util.Scanner;

public class Main_2563 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] map = new int[101][101];

        for(int i=0;i<n;i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            for(int p=0;p<10;p++){
                for(int q=0;q<10;q++){
                    map[x+p][y+q] = 1;
                }
            }
        }

        int cnt = 0;

        for(int i=1;i<=100;i++){
            for(int j=1;j<=100;j++){
                if(map[i][j] == 1) cnt++;
            }
        }

        System.out.println(cnt);

    }
}
