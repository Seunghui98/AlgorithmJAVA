// BOJ - 텀 프로젝트(9466번)
// DFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            connect = new boolean[n+1];

            for(int j=0;j<n;j++){
                int num = Integer.parseInt(st.nextToken());
                array[j+1] = num;
            }
            visited = new boolean[n+1];
            int cnt = 0;
            for(int i=1;i<=n;i++){
                if(visited[i]) {
                    if(!connect[i]) cnt++;
                    continue;
                }

                dfs(i, new ArrayList<>());
                if(!connect[i]) {
                    cnt++;
                }
            }

            sb.append(cnt+"\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int num, ArrayList<Integer> list){
        list.add(num);
        visited[num] = true;

        int con = array[num];

        if(visited[con]){
            int idx = -1;
            for(int i=0;i<list.size();i++){
                if(list.get(i) == con){
                    idx = i;
                    break;
                }
            }

            if(idx == -1) return;
            for(int i=idx;i<list.size();i++){
                connect[list.get(i)] = true;
            }
            return;
        }
        if(!visited[con]){
            dfs(con, list);
        }

    }
}

