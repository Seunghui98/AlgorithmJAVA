//SWEA - 파리퇴치(2001)
//완전탐색
import java.io.*;
import java.util.StringTokenizer;

public class Solution_2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] st = br.readLine().split(" ");
            int n = Integer.parseInt(st[0]);
            int m = Integer.parseInt(st[1]);
            int[][] map = new int[n][n];
            for(int i=0;i<n;i++){
                String[] s = br.readLine().split(" ");
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(s[j]);
                }

            }



            int ans = 0;
            for(int i=0;i<n-m+1;i++){
                for(int j=0;j<n-m+1;j++){
                    int cnt = 0;
                    for(int p=0;p<m;p++){
                        for(int q=0;q<m;q++){
                            cnt += map[i+p][j+q];
                        }
                    }
                    ans = Math.max(ans, cnt);
                }
            }
            bw.write(String.valueOf("#"+test_case+" "+ans+"\n"));


        }

        bw.flush();
        bw.close();

    }
}
