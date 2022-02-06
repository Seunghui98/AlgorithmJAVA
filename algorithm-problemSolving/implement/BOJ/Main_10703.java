// BOJ - 유성(10703)
// 구현(내리기)

import java.io.*;
import java.util.StringTokenizer;


public class Main_10703 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];

        for(int i=0;i<r;i++){
            String str = br.readLine();
            for(int j=0;j<c;j++){
                map[i][j] = str.charAt(j);
            }
        }

        int min_cnt = r;

        for(int j=0;j<c;j++){
            int cnt = 0;
            for(int i=0;i<r;i++){
                if(i <= r-1 && map[i][j] == 'X' && map[i+1][j] == '.'){
                    int x = i+1;

                    while (true) {
                        if (x == r - 1 || map[x][j] == '#') {
                            break;
                        }

                        if (map[x][j] == 'X') {
                            cnt = 0;
                            break;
                        }
                        x += 1;
                        cnt++;

                    }

                    }
                if(cnt > 0 && map[i][j] == '#'){
                    min_cnt = Math.min(cnt, min_cnt);
                }
            }

        }



            for(int j=0;j<c;j++){
                for(int i=r-1;i>=0;i--){
                    if(map[i][j] == 'X' && map[i+min_cnt][j] == '.'){
                        char temp = map[i][j];
                        map[i+min_cnt][j] = temp;
                        map[i][j] = '.';
                    }
                }
            }



            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    bw.write(String.valueOf(map[i][j]));
                }
                bw.write(String.valueOf("\n"));
            }
            bw.flush();
            bw.close();
        }
}
