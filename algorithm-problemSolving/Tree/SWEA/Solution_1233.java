// SWEA - 사칙연산 유효성 검사(1233번)
// 이진트리(BFS)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1233 {
    static String[] nodes;
    static int n, lastIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int test_case=1;test_case<=T;test_case++){
            n = Integer.parseInt(br.readLine());
            nodes = new String[n+1];
            lastIndex = 0;
            for(int i=0;i<n;i++){
                String[] data = br.readLine().split(" ");
                nodes[++lastIndex] = data[1];
            }

            int ans = bfs();
            System.out.println("#"+test_case+" "+ans);
        }
    }

    public static boolean isEmpty(){
        return lastIndex==0;
    }

    public static boolean isFull(){
        return lastIndex==n;
    }

    public static int bfs(){
        if(isEmpty()) return 1;

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.offer(1);
        while (!queue.isEmpty()){
            int current = queue.poll();
            if(nodes[current].equals("+") || nodes[current].equals("-") || nodes[current].equals("*") || nodes[current].equals("/")){
                if(current*2 <= lastIndex){
                    queue.offer(current*2);
                }
                if(current*2+1 <= lastIndex){
                    queue.offer(current*2+1);
                }
            } else {
                if(current*2 <= lastIndex){
                    int child = current*2;
                    if(nodes[child].equals("+") || nodes[child].equals("-") || nodes[child].equals("*") || nodes[child].equals("/")){
                        return 0;
                    }
                    queue.offer(current*2);
                }
                if(current*2+1 <= lastIndex){
                    int child = current*2+1;
                    if(nodes[child].equals("+") || nodes[child].equals("-") || nodes[child].equals("*") || nodes[child].equals("/")){
                        return 0;
                    }
                    queue.offer(current*2+1);
                }
            }


        }
        return 1;
    }

}
