import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9466 {
    public static boolean[] visited;
    public static int[] array;
    public static boolean[] connect;
    public static boolean[] team;
    public static int n;
    public static boolean check;
    public static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            array = new int[n+1];
            connect = new boolean[n+1];

            for(int j=0;j<n;j++){
                int num = Integer.parseInt(st.nextToken());
                array[j+1] = num;
            }
            visited = new boolean[n+1];
            answer = 0;

            for(int i=1;i<=n;i++){
                if(!connect[i]){
                    dfs(i);
                }
            }

            sb.append((n-answer)+"\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int num){
        if(visited[num]){
            connect[num] = true;
            answer++;
        } else {
            visited[num] = true;
        }

        if(!connect[array[num]]){
            dfs(array[num]);
        }

        visited[num] = false;
        connect[num] = true;

    }
}
