// BOJ - 온풍기 안녕!(23289)
// 구현(BFS)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_23289 {
    public static int r, c, k;
    public static int[][] map;
    public static int[][] heat;
    public static ArrayList<Node> heater;
    public static HashMap<Node, Integer> wall;
    // 오른-왼-위-아래
    public static int[] r_dx = {-1, 0, 1};
    public static int[] r_dy = {1, 1, 1};
    public static int[] l_dx = {-1, 0, 1};
    public static int[] l_dy = {-1, -1, -1};
    public static int[] u_dx = {-1 ,-1, -1};
    public static int[] u_dy = {-1, 0, 1};
    public static int[] d_dx = {1, 1, 1};
    public static int[] d_dy = {-1, 0, 1};

    public static class Node {
        int x;
        int y;
        int type;
        int h;
        int dir;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public Node(int x, int y, int h, int dir){
            this.x = x;
            this.y = y;
            this.h = h;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", type=" + type +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && type == node.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, type);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        heat = new int[r][c];
        heater = new ArrayList<>();
        wall = new HashMap<>();
        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<c;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 4){
                    heater.add(new Node(i, j));
                }
            }
        }

        int w = Integer.parseInt(br.readLine());
        for(int i=0;i<w;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            wall.put(new Node(x-1, y-1, type), 1);
        }


        int cnt = 0;
        while (true){

            // 온풍기 바람 나옴
            for(Node node:heater){
                int dir = map[node.x][node.y];
                if(dir== 1){
                    int nx = node.x;
                    int ny = node.y + 1;
                    if(ny < c){
                        wind(dir, nx, ny);
                    }
                } else if(dir==2){
                    int nx = node.x;
                    int ny = node.y - 1;
                    if(ny >= 0){
                        wind(dir, nx, ny);
                    }
                } else if(dir==3){
                    int nx = node.x - 1;
                    int ny = node.y;
                    if(nx >= 0){
                        wind(dir, nx, ny);
                    }
                } else if(dir==4) {
                    int nx = node.x + 1;
                    int ny = node.y;
                    if(nx < r){
                        wind(dir, nx, ny);
                    }
                }


            }


            // 온도 조절
            int[][] heat_copy = new int[r][c];

            // 위 - 오 - 아 - 왼
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(0 <= nx && nx <r && 0 <= ny && ny < c){
                            int diff = heat[i][j] - heat[nx][ny];
                                if (diff > 0) {
                                    if (d == 0) {
                                        if (!wall.containsKey(new Node(i, j, 0))) {
                                            heat_copy[nx][ny] += (diff / 4);
                                            heat_copy[i][j] -= (diff / 4);

                                        }
                                    } else if (d == 1) {
                                        if (!wall.containsKey(new Node(i, j, 1))) {
                                            heat_copy[nx][ny] += (diff / 4);
                                            heat_copy[i][j] -= (diff / 4);

                                        }
                                    } else if (d == 2) {
                                        if (!wall.containsKey(new Node(i + 1, j, 0))) {
                                            heat_copy[nx][ny] += (diff / 4);
                                            heat_copy[i][j] -= (diff / 4);

                                        }
                                    } else {
                                        if (!wall.containsKey(new Node(i, j - 1, 1))) {
                                            heat_copy[nx][ny] += (diff / 4);
                                            heat_copy[i][j] -= (diff / 4);

                                        }
                                    }
                                }
                        }
                    }
                }
            }

            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    heat[i][j] += heat_copy[i][j];
                }
            }

            // 바깥쪽 온도 1씩 감소
            down();
            // 초콜릿 먹기
            cnt++;
            if(cnt > 100){
                cnt = 101;
                break;
            }

            if(check()){
                break;
            }
        }



        System.out.println(cnt);

    }

    public static boolean check(){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j] == 5 && heat[i][j] < k){
                    return false;
                }
            }
        }
        return true;
    }

    public static void down(){
        boolean[][] visited = new boolean[r][c];
        // 1행
        for(int i=0;i<c;i++){
            if(!visited[0][i]){
                visited[0][i] = true;
                if(heat[0][i] >= 1){
                    heat[0][i] -= 1;
                } else {
                    heat[0][i] = 0;
                }
            }
        }
        // 마지막 행
        for(int i=0;i<c;i++){
            if(!visited[r-1][i]){
                visited[r-1][i] = true;
                if(heat[r-1][i] >= 1){
                    heat[r-1][i] -= 1;
                } else {
                    heat[r-1][i] = 0;
                }
            }
        }
        // 1열
        for(int i=0;i<r;i++){
            if(!visited[i][0]){
                visited[i][0] = true;
                if(heat[i][0] >= 1){
                    heat[i][0] -= 1;
                } else {
                    heat[i][0] = 0;
                }
            }
        }
        // 마지막 열
        for(int i=0;i<r;i++){
            if(!visited[i][c-1]){
                visited[i][c-1] = true;
                if(heat[i][c-1] >= 1){
                    heat[i][c-1] -= 1;
                } else {
                    heat[i][c-1] = 0;
                }
            }
        }
    }

    public static void wind(int dir, int x, int y){
        Queue<Node> q = new LinkedList<>();
        dir -= 1;
        heat[x][y] += 5;
        boolean[][] visited = new boolean[r][c];
        q.add(new Node(x, y, 5, dir));
        visited[x][y] = true;
        int[] dx;
        int[] dy;
        // 오른쪽
        if(dir == 0){
            dx = r_dx;
            dy = r_dy;
        }
        // 왼쪽
        else if(dir == 1){
            dx = l_dx;
            dy = l_dy;
        }
        // 위
        else if(dir == 2){
            dx = u_dx;
            dy = u_dy;
        }
        // 아래
        else {
            dx = d_dx;
            dy = d_dy;
        }
        while (!q.isEmpty()){
            Node node = q.poll();
            if(node.h == 0){
                return;
            }
            for(int d=0;d<3;d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(0 <= nx && nx < r && 0 <= ny && ny < c){
                    if(!visited[nx][ny]){
                        if(d == 0){
                            if(dir == 0){
                                if(!wall.containsKey(new Node(node.x, node.y, 0)) && !wall.containsKey(new Node(node.x-1, node.y, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }


                            } else if(dir == 1){
                                if(!wall.containsKey(new Node(node.x, node.y, 0)) && !wall.containsKey(new Node(node.x-1, node.y-1, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }

                            } else if(dir == 2){
                                if(!wall.containsKey(new Node(node.x, node.y-1, 0)) && !wall.containsKey(new Node(node.x, node.y-1, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else {
                                if(!wall.containsKey(new Node(node.x, node.y-1, 1)) && !wall.containsKey(new Node(node.x+1, node.y-1, 0))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            }
                        } else if(d == 1){
                            if(dir == 0){
                                if(!wall.containsKey(new Node(node.x, node.y, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else if(dir == 1){
                                if(!wall.containsKey(new Node(node.x, node.y-1, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else if(dir == 2){
                                if(!wall.containsKey(new Node(node.x, node.y, 0))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else {
                                if(!wall.containsKey(new Node(node.x+1, node.y, 0))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            }
                        } else {
                            if(dir == 0){
                                if(!wall.containsKey(new Node(node.x+1, node.y, 0)) && !wall.containsKey(new Node(node.x+1, node.y, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else if(dir == 1){
                                if(!wall.containsKey(new Node(node.x+1, node.y, 0)) && !wall.containsKey(new Node(node.x+1, node.y-1, 1))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else if(dir == 2){
                                if(!wall.containsKey(new Node(node.x, node.y, 1)) && !wall.containsKey(new Node(node.x, node.y+1, 0))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            } else {
                                if(!wall.containsKey(new Node(node.x, node.y, 1)) && !wall.containsKey(new Node(node.x+1, node.y+1, 0))){
                                    q.offer(new Node(nx, ny, node.h-1, dir));
                                    heat[nx][ny] += node.h-1;
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }
                }
            }
        }

    }



}
