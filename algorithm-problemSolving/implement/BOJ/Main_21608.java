// BOJ - 상어 초등학교(21608번)
// 구현
import java.io.*;
import java.util.*;

public class Main_21608 {
    static class Student {
        int stu_num;
        int[] stu_like;

        public Student(int stu_num, int[] stu_like){
            this.stu_num = stu_num;
            this.stu_like = stu_like;
        }

    }


    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static ArrayList<Student> stu;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stu = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n*n;i++){
            String[] like = br.readLine().split(" ");

            int[] l = new int[4];
            for(int j=1;j<5;j++){
                l[j-1] = Integer.parseInt(like[j]);
            }
            Student s = new Student(Integer.parseInt(like[0]), l);
            stu.add(s);
        }
        int[][] empty = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                empty[i][j] = 4;
                if(i == 0 || i == n-1)
                    empty[i][j]--;
                if(j == 0 || j == n-1)
                    empty[i][j]--;
            }
        }
        int[][] map = new int[n][n];
        map[1][1] = stu.get(0).stu_num;
        empty[0][1]--;
        empty[2][1]--;
        empty[1][0]--;
        empty[1][2]--;


        for(int k=1;k<n*n;k++){
            Student  s = stu.get(k);
            int[][] like = new int[n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j] != 0)
                        continue;
                    int cnt = 0;

                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                            for (int s_n : s.stu_like) {
                                if(s_n == map[nx][ny]){
                                    like[i][j]++;
                                }
                            }
                        }
                    }


                }
            }
            int max_empty = -1;
            int max_like = -1;
            int row = -1;
            int col = -1;

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j] != 0)
                        continue;
                    if(like[i][j] > max_like){
                        max_like = like[i][j];
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    } else if(like[i][j] == max_like && empty[i][j] > max_empty){
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    }
                }
            }

            map[row][col] = s.stu_num;
            for(int d=0;d<4;d++){
                int nx = row + dx[d];
                int ny = col + dy[d];
                if(0 <= nx && nx < n && 0 <= ny && ny < n){
                    empty[nx][ny]--;
                }
            }


        }


        int like_score = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int cnt = 0;
                for(int k=0;k<stu.size();k++){
                    if(stu.get(k).stu_num == map[i][j]){
                        for(int d=0;d<4;d++){
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if(0 <= nx && nx < n && 0 <= ny && ny < n){
                                for(int s_n:stu.get(k).stu_like){
                                    if(map[nx][ny] == s_n){
                                        cnt++;
                                    }
                                }
                            }
                        }

                    }
                }

                if(cnt == 1){
                    like_score++;
                } else if(cnt == 2){
                    like_score += 10;
                } else if(cnt == 3){
                    like_score += 100;
                } else if(cnt == 4){
                    like_score += 1000;
                }

            }
        }

        bw.write(String.valueOf(like_score));
        bw.flush();
        bw.close();
    }
}
