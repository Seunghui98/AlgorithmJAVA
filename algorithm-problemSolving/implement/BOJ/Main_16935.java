// BOJ - 배열 돌리기3 (16935번)
// 구현
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16935 {
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int r = Integer.parseInt(input[2]);
        map = new int[n][m];
        for(int i=0;i<n;i++){
            String[] data = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(data[j]);
            }
        }
        String[] s = br.readLine().split(" ");
        for(int k=0;k<r;k++){
            int dir = Integer.parseInt(s[k]);
            if(dir == 1){
                for(int j=0;j<map[0].length;j++){
                    for(int i=0;i<map.length/2;i++){
                        int temp = map[i][j];
                        map[i][j] = map[map.length-i-1][j];
                        map[map.length-i-1][j] = temp;
                    }
                }
            } else if (dir == 2){
                for(int i=0;i<map.length;i++){
                    for(int j=0;j<map[0].length/2;j++){
                        int temp = map[i][j];
                        map[i][j] = map[i][map[0].length-j-1];
                        map[i][map[0].length-j-1] = temp;
                    }
                }
            } else if(dir == 3){
                int[][] temp = new int[map[0].length][map.length];

                for(int i=0;i<map.length;i++){
                    for(int j=0;j<map[0].length;j++){
                        temp[j][map.length-i-1] = map[i][j];
                    }
                }

                map = temp;
            } else if(dir == 4){
                int[][] temp = new int[map[0].length][map.length];

                for(int i=0;i<temp.length;i++){
                    for(int j=0;j<temp[0].length;j++){
                        temp[i][j] = map[j][temp.length-i-1];
                    }
                }

                map = temp;
            } else if(dir == 5){
                n = map.length;
                m = map[0].length;
                int[][] temp = new int[n][m];
                // 1 -> 2
                for(int i=0;i<n/2;i++){
                    for(int j=0;j<m/2;j++){
                        temp[i][m/2+j] = map[i][j];
                    }
                }
                // 2 -> 3
                for(int j=m/2;j<m;j++){
                    for(int i=0;i<n/2;i++){
                        temp[i+n/2][j] = map[i][j];
                    }
                }
                // 3 -> 4
                for(int i=n/2;i<n;i++){
                    for(int j=m/2;j<m;j++){
                        temp[i][j-m/2] = map[i][j];
                    }
                }
                // 4 -> 1
                for(int j=0;j<m/2;j++){
                    for(int i=n/2;i<n;i++){
                        temp[i-n/2][j] = map[i][j];
                    }
                }
                map = temp;
            } else if(dir == 6){
                n = map.length;
                m = map[0].length;
                int[][] temp = new int[n][m];
                // 1 -> 4
                for(int i=0;i<n/2;i++){
                    for(int j=0;j<m/2;j++){
                        temp[i+n/2][j] = map[i][j];
                    }
                }
                // 4 -> 3
                for(int i=n/2;i<n;i++){
                    for(int j=0;j<m/2;j++){
                        temp[i][j+m/2] = map[i][j];
                    }
                }
                // 3 -> 2
                for(int i=n/2;i<n;i++){
                    for(int j=m/2;j<m;j++){
                        temp[i-n/2][j] = map[i][j];
                    }
                }

                // 2 -> 1
                for(int i=0;i<n/2;i++){
                    for(int j=m/2;j<m;j++){
                        temp[i][j-m/2] = map[i][j];
                    }
                }
                map = temp;
            }
        }

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j] +" ");
            }
            System.out.println();
        }
    }
}
