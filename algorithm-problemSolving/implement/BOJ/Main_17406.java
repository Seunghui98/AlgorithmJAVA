// BOJ - 배열돌리기4(17406번)
// 구현
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_17406 {
    static int[][] map;
    static int[][] map2;
    static int r, c, k;
    static List<int[]> permutation_list;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] data = br.readLine().split(" ");
        r = Integer.parseInt(data[0]);
        c = Integer.parseInt(data[1]);
        k = Integer.parseInt(data[2]);
        map = new int[r][c];
        for(int i=0;i<r;i++){
            String[] input = br.readLine().split(" ");
            for(int j=0;j<c;j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        int[] arr = new int[k];
        List<int[]> operation = new ArrayList<>();
        for(int i=0;i<k;i++){
            arr[i] = i;
            String[] input = br.readLine().split(" ");
            int[] oper = new int[3];
            oper[0] = Integer.parseInt(input[0]);
            oper[1] = Integer.parseInt(input[1]);
            oper[2] = Integer.parseInt(input[2]);
            operation.add(oper);

        }
        permutation_list = new ArrayList<>();
        permutation(arr, new int[k], new boolean[k], 0);

        map2 = new int[r][c];
        int ans_min = Integer.MAX_VALUE;
        for(int[] p:permutation_list){
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    map2[i][j] = map[i][j];
                }
            }

            for(int index:p){
                int[] arr_rota = operation.get(index);
                rotation(arr_rota[0], arr_rota[1], arr_rota[2]);

            }

            int[] sum = new int[r];
            for(int i=0;i<r;i++){
                int cnt = 0;
                for(int j=0;j<c;j++){
                    cnt += map2[i][j];
                }
                sum[i] = cnt;
            }

            Arrays.sort(sum);
            ans_min = Math.min(ans_min, sum[0]);
        }

        bw.write(String.valueOf(ans_min+"\n"));
        bw.flush();
        bw.close();


    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int depth){
        if(depth == k){
            int[] arr_copy = new int[k];
            for(int i=0;i<k;i++){
                arr_copy[i] = output[i];
            }

            permutation_list.add(arr_copy);
            return;
        }

        for(int i=0;i<k;i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth+1);
                visited[i] = false;
            }
        }
    }

    public static void rotation(int x, int y, int s){

        for(int i=0;i<s;i++){
            int f_a = x-s+i-1;
            int f_b = y-s+i-1;
            int a = f_a;
            int b = f_b;
            int temp = map2[a][b];

            outer : for(int d=0;d<4;d++){
                int nx = a;
                int ny = b;
                while (true){
                    nx += dx[d];
                    ny += dy[d];
                    if(nx < f_a || ny < f_b || nx > f_a+2*s-2*i || ny > f_b+2*s-2*i ){
                        continue outer;
                    }

                    map2[a][b] = map2[nx][ny];
                    a = nx;
                    b = ny;
                    if(a == f_a && b == f_b){
                        break outer;
                    }

                }
            }
            map2[a][b+1] = temp;
        }

    }
}
