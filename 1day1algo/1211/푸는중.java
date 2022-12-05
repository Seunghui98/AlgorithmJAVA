import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1043 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] check = new boolean[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());
        for(int i=0;i<t;i++){
            int p = Integer.parseInt(st.nextToken());
            check[p] = true;
        }
        parent = new int[m+1];
        ArrayList<Integer>[] party = new ArrayList[m+1];
        for(int i=0;i<m+1;i++){
            parent[i] = i;
            party[i] = new ArrayList<>();
        }


        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            boolean check_p = false;
            for(int j=0;j<num;j++){
                int p = Integer.parseInt(st.nextToken());
                list.add(p);
                if(check[p]) check_p = true;
            }
            party[i] = list;
            if(check_p){
                for(int l:list){
                    check[l] = true;
                }
            }
        }
        int cnt = 0;
        for(int i=1;i<=m;i++){
            ArrayList<Integer> list = party[i];
            boolean check_p = false;
            for(int l:list){
                if(check[l]) {
                    check_p = true;
                    break;
                }
            }

            if(check_p){
                cnt++;
            }
        }

        System.out.println(m-cnt);


    }

    public static int find_parent(int x){
        if(parent[x] == x) return x;
        return parent[x] = find_parent(parent[x]);
    }

    public static void union(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        if(a <= b){
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }


}
