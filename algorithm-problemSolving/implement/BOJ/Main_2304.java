// BOJ - 창고 다각형(2304번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int x;
        int height;

        public Node(int x, int height){
            this.x = x;
            this.height = height;
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        ArrayList<Node> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x, y));
        }
        Collections.sort(list);
        int sum = 0;
        int max_x = 0;
       // 왼쪽 -> 오른쪽
        Node current = list.get(0);
        for(int i=1;i<n;i++){
            if(current.height <= list.get(i).height){
                sum += (list.get(i).x - current.x) * current.height;
                current = list.get(i);
                max_x = i;
            }
        }

        // 오른쪽 -> 왼쪽
        current = list.get(n-1);
        for(int i=0;i<n-max_x;i++){
            if(current.height <= list.get(n-i-1).height){
                sum += (current.x - list.get(n-i-1).x) * current.height;
                current = list.get(n-i-1);

            }
        }

        sum += list.get(max_x).height;
        System.out.println(sum);

    }
}
