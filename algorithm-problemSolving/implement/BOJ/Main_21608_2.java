// BOJ - 상어 초등학교 (21608번)
// 구현 

import java.io.*;
import java.util.*;

public class Main_21608_2 {
    static class Student {
        int stu_num;
        int[] stu_like;

        public Student(int stu_num, int[] stu_like){
            this.stu_num = stu_num;
            this.stu_like = stu_like;
        }

    }


    static class Seat implements Comparable<Seat>{
        int row;
        int col;
        int like_cnt;
        int empty_cnt;
        public Seat(int row, int col, int like_cnt, int empty_cnt){
            this.row = row;
            this.col = col;
            this.like_cnt = like_cnt;
            this.empty_cnt = empty_cnt;
        }

        @Override
        public int compareTo(Seat o) {
            if(this.like_cnt == o.like_cnt){
                if(this.empty_cnt == o.empty_cnt){
                    if(this.row == o.row){
                        return this.col - o.col;
                    } else {
                        return this.row - o.row;
                    }
                } else{
                    return -(this.empty_cnt - o.empty_cnt);
                }
            }
            return -(this.like_cnt - o.like_cnt);
        }


    }


    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static ArrayList<Student> stu;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stu = new ArrayList<>();
        Map<Integer, Student> h_stu = new HashMap<>();


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
//        int[][] empty = new int[n][n];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                empty[i][j] = 4;
//                if(i == 0 || i == n-1)
//                    empty[i][j]--;
//                if(j == 0 || j == n-1)
//                    empty[i][j]--;
//            }
//        }
        int[][] map = new int[n][n];
        map[1][1] = stu.get(0).stu_num;
//        empty[0][1]--;
//        empty[2][1]--;
//        empty[1][0]--;
//        empty[1][2]--;


        for(int k=1;k<n*n;k++){
            Student  s = stu.get(k);
            ArrayList<Seat> seat_list = new ArrayList<>();
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    int sum_like = 0;
                    int sum_empty = 0;
                    if(map[i][j] != 0) continue;

                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1){
                            continue;
                        }

                        for(int l:s.stu_like){
                            if(map[nx][ny] == l){
                                sum_like += 1;
                            }
                        }

                        if(map[nx][ny] == 0){
                            sum_empty++;
                        }

                    }

                    seat_list.add(new Seat(i, j, sum_like, sum_empty));
                }
            }
            Collections.sort(seat_list);
            int row = seat_list.get(0).row;
            int col = seat_list.get(0).col;

            map[row][col] = s.stu_num;

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
