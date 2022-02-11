// BOJ - 봄버맨(16918번)
// 구현
import java.io.*;

public class Main_16918 {
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int r, c, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str = br.readLine().split(" ");
        r = Integer.parseInt(str[0]);
        c = Integer.parseInt(str[1]);
        n = Integer.parseInt(str[2]);
        map = new int[r][c];
        for(int i=0;i<r;i++){
            String[] data = br.readLine().split("");
            for(int j=0;j<c;j++){
                if(data[j].equals(".")){
                    map[i][j] = 0;
                } else if(data[j].equals("O")){
                    map[i][j] = 1;
                }
            }
        }
        int time = n;
        increase();
        time--;

        while (true){
            if(time == 0){
                break;
            }
            increase();
            install();

            time--;
            if(time == 0){
                break;
            }
            bomb();
            increase();
            time--;

        }


        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j] == 0){
                    bw.write(String.valueOf("."));
                } else{
                    bw.write(String.valueOf("O"));
                }
            }
            bw.write(String.valueOf("\n"));

        }
    bw.flush();
    bw.close();

    }

    public static void increase() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    map[i][j] += 1;
                }

            }
        }
    }

    public static void install(){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                }
            }
        }
    }

    public static void bomb(){
        boolean[][] check = new boolean[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j] == 3){
                    check[i][j] = true;
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(0<= nx && nx < r && 0 <= ny && ny < c){
                            check[nx][ny] = true;
                        }
                    }
                }

            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(check[i][j]) map[i][j] = 0;
            }
        }
    }

}
