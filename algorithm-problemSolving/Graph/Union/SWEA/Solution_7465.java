// SWEA - 창용 마을 무리의 개수(7465번)
// Union

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution_7465 {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] data = br.readLine().split(" ");
            N = Integer.parseInt(data[0]);
            M = Integer.parseInt(data[1]);
            parent = new int[N+1];
            make();
            for(int i=0;i<M;i++){
                String[] input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                union(a, b);
            }

            HashSet<Integer> set = new HashSet<>();
            for(int i=1;i<=N;i++){
                int p = findParent(i);
                if(!set.contains(p)){
                    set.add(p);
                }
            }
            sb.append("#").append(test_case).append(" ").append(set.size()).append("\n");

        }
        System.out.println(sb.toString());
    }

    public static void make(){
        for(int i=0;i<=N;i++){
            parent[i] = i;
        }
    }

    public static int findParent(int x){
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);
        if(parentA == parentB) return;
        if(parentA < parentB){
            parent[parentB] = parentA;
        } else{
            parent[parentA] = parentB;
        }
    }


}
