import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466 {
    public static boolean[] visited;
    public static int[] array;
    public static boolean[] connect;
    public static int n;
    public static boolean check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            array = new int[n+1];
            visited = new boolean[n+1];
            connect = new boolean[n+1];

            for(int j=0;j<n;j++){
                int num = Integer.parseInt(st.nextToken());
                array[j+1] = num;
            }

            int cnt = 0;
            for(int i=1;i<=n;i++){
                if(connect[i]) continue;
                check = false;
                dfs(i, i);
                if(!check) {
                    cnt++;
                }
            }

            sb.append(cnt+"\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int first, int num){
        visited[num] = true;

        int con = array[num];

        if(first == con){
            check = true;
            connect[num] = true;
            return;
        }
        if(!visited[con]){
            dfs(first, con);
            if(check) connect[num] = true;
        }
        visited[num] = false;
    }
}
