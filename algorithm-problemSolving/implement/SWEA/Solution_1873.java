// BOJ - 상호의 배틀필드(1873번)
// 구현
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Solution_1873 {
    public static class Pos{
        private int x;
        private int y;
        private int dir;
        public Pos(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static char[][] map;
    static List<Pos> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int h;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] str = br.readLine().split(" ");
            h = Integer.parseInt(str[0]);
            w = Integer.parseInt(str[1]);
            map = new char[h][w];
            list = new LinkedList<>();
            for(int i=0;i<h;i++){
                String[] s = br.readLine().split("");
                for(int j=0;j<s.length;j++){
                    map[i][j] = s[j].charAt(0);
                    if(map[i][j] == '^'){
                        list.add(new Pos(i, j, 0));
                    } else if(map[i][j] == 'v'){
                        list.add(new Pos(i, j, 1));
                    } else if(map[i][j] == '<'){
                        list.add(new Pos(i, j, 2));
                    } else if(map[i][j] == '>'){
                        list.add(new Pos(i, j, 3));
                    }
                }

            }



            int game = Integer.parseInt(br.readLine());
            String[] move = br.readLine().split("");
            for(int i=0;i<game;i++){
                if(move[i].equals("S")){
                    shoot();
                } else {
                    dir_moving(move[i]);
                }


            }

            bw.write(String.valueOf("#"+test_case+" "));
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    bw.write(String.valueOf(map[i][j]));
                }
                bw.write(String.valueOf("\n"));
            }



        }

        bw.flush();
        bw.close();
    }

    public static void dir_moving(String d){
        int change_d = -1;
        char car = '^';
        if(d.equals("U")){
            change_d = 0;
        } else if(d.equals("D")){
            change_d = 1;
            car = 'v';
        } else if(d.equals("L")){
            change_d = 2;
            car = '<';
        } else if(d.equals("R")){
            change_d = 3;
            car = '>';
        }
        for(int i=0;i<list.size();i++){
            list.get(i).dir = change_d;
            map[list.get(i).x][list.get(i).y] = car;
            int nx = list.get(i).x + dx[change_d];
            int ny = list.get(i).y + dy[change_d];
            if(nx >= 0 && nx < h && ny >= 0 && ny < w){
                if(map[nx][ny] == '.'){
                    map[nx][ny] = car;
                        map[list.get(i).x][list.get(i).y] = '.';
                        list.get(i).x = nx;
                        list.get(i).y = ny;
                    }
            }
        }
    }


    public static void shoot(){
        for(int i=0;i<list.size();i++){
            int x = list.get(i).x;
            int y = list.get(i).y;
            int dir = list.get(i).dir;
            int nx = x;
            int ny = y;
            while(true){
                nx += dx[dir];
                ny += dy[dir];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '#'){
                    break;
                }

                if(map[nx][ny] == '.'){
                    continue;
                }

                if(map[nx][ny] =='*'){
                    map[nx][ny] = '.';
                    break;
                }
            }
        }
    }
}
