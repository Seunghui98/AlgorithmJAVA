// SWEA - 3289(서로소 집합)
// 합집합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_3289 {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            parent = new int[N+1];
            make();
            sb.append("#").append(test_case).append(" ");
            for(int i=0;i<M;i++) {
                String[] data = br.readLine().split(" ");
                int cal = Integer.parseInt(data[0]);
                int a = Integer.parseInt(data[1]);
                int b = Integer.parseInt(data[2]);
                if(cal == 0){
                    union(a, b);
                } else if (cal ==1 ){
                    if(findParent(a) == findParent(b)){
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                }
            }
            sb.append("\n");

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
        if(parentA > parentB) parent[parentA] = parentB;
        else parent[parentB] = parent[parentA];
    }


}
