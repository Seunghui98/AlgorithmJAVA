// BOJ - 거짓말(1043번)
// union find
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Main_1043 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] know = new boolean[n+1];
        HashSet<Integer>[] party = new HashSet[m+1];
        parent = new int[n+1];
        for(int i=1;i<=m;i++){
            party[i] = new HashSet<>();
        }
        st = new StringTokenizer(br.readLine(), " ");
        int know_cnt = Integer.parseInt(st.nextToken());
        for(int i=1;i<=know_cnt;i++){
            int tmp = Integer.parseInt(st.nextToken());
            know[tmp] = true;
        }

        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        // 진실을 아는 사람과 같은 파티 공간에 있으면 진실을 아는 것으로 체크
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            if(num <= 0) continue;
            int pre = Integer.parseInt(st.nextToken());
            party[i].add(pre);
            for(int j=0;j<num-1;j++){
                int p = Integer.parseInt(st.nextToken());
                if(find_parent(pre) != find_parent(p)){
                    union(pre, p);
                }

                party[i].add(p);
                pre = p;
            }
        }

        // 진실을 아는 사람과 연관관계가 있음
        boolean[] check = new boolean[n+1];
        for(int i=1;i<=n;i++){
            if(know[i] && !check[i]){
                int p = find_parent(i);
                for(int j=1;j<=n;j++){
                    if(find_parent(j) == p){
                        know[j] = true;
                        check[j] = true;
                    }
                }
            }
        }

        int answer = 0;
        for(int i=1;i<=m;i++){
            boolean c = false;
            for(int p:party[i]){
                if(know[p]){
                    c = true;
                    break;
                }
            }
            if(!c) answer++;
        }

        System.out.println(answer);
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
