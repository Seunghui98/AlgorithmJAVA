// BOJ - 마법사 상어와 복제(23290번)
// DFS(중복조합) + 시뮬레이션

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_23290 {
    public static int m, s;
    public static int s_x, s_y;
    public static int[][] smell;
    public static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static ArrayList<ArrayList<ArrayList<Integer>>> map;
    public static ArrayList<Integer> moving_list;
    public static int max_eat;
    public static int[] s_dx = {-1, 0, 1, 0};
    public static int[] s_dy = {0, -1, 0, 1};
    public static class Fish{
        int x;
        int y;
        int d;
        public Fish(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        smell = new int[4][4];
        Queue<Fish> q_first = new LinkedList<>();

        map = new ArrayList<>();
        for(int i=0;i<4;i++){
            map.add(new ArrayList<>());
            for(int j=0;j<4;j++){
                map.get(i).add(new ArrayList<>());
            }
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.get(x-1).get(y-1).add(d-1);
        }

        st = new StringTokenizer(br.readLine(), " ");
        s_x = Integer.parseInt(st.nextToken()) -1;
        s_y = Integer.parseInt(st.nextToken()) -1;

        int time = 1;
        while (time<=s){
            // 복제
            q_first.clear();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    for(int k=0;k<map.get(i).get(j).size();k++){
                        q_first.offer(new Fish(i, j, map.get(i).get(j).get(k)));
                    }
                }
            }

            // 물고기 이동
            ArrayList<ArrayList<ArrayList<Integer>>> map_copy = new ArrayList<>();
            for(int i=0;i<4;i++){
                map_copy.add(new ArrayList<>());
                for(int j=0;j<4;j++){
                    map_copy.get(i).add(new ArrayList<>());
                }
            }

            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    for(int k=0;k<map.get(i).get(j).size();k++){
                        int first_dir =  map.get(i).get(j).get(k);
                        boolean check = true;
                        int nx = i, ny = j;

                        for(int d=0;d<8;d++){
                            int nd = ((first_dir-d)+8) % 8;
                            nx = i + dx[nd];
                            ny = j + dy[nd];
                            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                            if(smell[nx][ny] != 0 && time - smell[nx][ny] <= 2) continue;
                            if(nx == s_x && ny == s_y) continue;
                            map_copy.get(nx).get(ny).add(nd);
                            check = false;
                            break;
                        }

                        if(check){
                            map_copy.get(i).get(j).add(first_dir);
                        }

                    }

                }
            }

            map = map_copy;

            // 상어의 이동 -> 중복 순열...dfs
            max_eat = 0;
            moving_list = new ArrayList<>();
            dfs(0, new int[3]);
            Collections.sort(moving_list);
            String moving_str = String.valueOf(moving_list.get(0));
                for (int i = 0; i < 3; i++) {
                    int dir = moving_str.charAt(i) - '1';
                    s_x += s_dx[dir];
                    s_y += s_dy[dir];
                    // 죽이면서 냄새 뿌리기
                    if (map.get(s_x).get(s_y).size() > 0) {
                        map.get(s_x).get(s_y).clear();
                        smell[s_x][s_y] = time;
                    }

                }

            // 복제 마법

            while (!q_first.isEmpty()){
                Fish f = q_first.poll();
                map.get(f.x).get(f.y).add(f.d);
            }


            time++;
        }


        // 물고기 갯수 출력
        int ans = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                ans += map.get(i).get(j).size();
            }
        }
        System.out.println(ans);
    }

    // 상어가 이동할 수 있는 3연속 방향의 중복순열을 구하는 dfs
    public static void dfs(int depth, int[] arr){
        if(depth==3){
            int[] arr_copy = new int[3];
            String str = "";
            for(int i=0;i<3;i++){
                arr_copy[i] = arr[i];
                str += String.valueOf(arr_copy[i]);
            }

            int cnt = eatCount(str);

            if(max_eat < cnt){
                moving_list.clear();
                moving_list.add(Integer.parseInt(str));
                max_eat = cnt;
            } else if (max_eat == cnt){
                moving_list.add(Integer.parseInt(str));
            }

            return;
        }
        for(int i=1;i<=4;i++){
            arr[depth] = i;
            dfs(depth+1, arr);
        }
    }

    // 해당 방향으로 3연속 이동할 때 먹을 수 있는 물고기 갯수 카운트
    public static int eatCount(String dir){
        int x = s_x;
        int y = s_y;
        int eat_cnt = 0;
        boolean[][] vistied = new boolean[4][4];

        for(int i=0;i<3;i++){
            int d = dir.charAt(i) - '1';
            x += s_dx[d];
            y += s_dy[d];
            if(x < 0 || y < 0 || x >= 4 || y >= 4){
                return -1;
            }
            if(!vistied[x][y]) eat_cnt += map.get(x).get(y).size();
            vistied[x][y] = true;
        }
        return eat_cnt;
    }
}
