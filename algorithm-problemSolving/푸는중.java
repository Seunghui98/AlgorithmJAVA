import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16562 {
    public static int n, m, k;
    public static int[] money;
    public static ArrayList[] friend;
    public static int[] parent;
    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        money = new int[n+1];
        friend = new ArrayList[n+1];
        parent = new int[n+1];
        visited = new boolean[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n+1;i++){
            friend[i] = new ArrayList<Integer>();
            money[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a].add(b);
            friend[b].add(a);

            if(find_parent(a) != find_parent(b)){

            }
        }

        for(int i=0;i<)


    }

    public static int find_parent(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find_parent(parent[a]);
    }

    public static void union(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        parent[b] = a;
    }
}
