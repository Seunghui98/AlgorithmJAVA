// SWEA - 퍼펙트 셔플(3499)
// Queue

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_3499 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++){
            Queue<String> q = new LinkedList<>();
            int n = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(" ");
            int mid = 0;
            if(n%2 == 0) {
                mid = n / 2;
            } else {
                mid = n / 2 +1;
            }
            for(int i=0;i<data.length/2;i++){
                q.add(data[i]);
                q.add(data[i+mid]);
            }

            if(n%2==1){
                q.add(data[mid-1]);
            }

            System.out.print("#"+test_case+" ");
            for(int i=0;i<n;i++){
                System.out.print(q.poll()+" ");
            }
            System.out.println();

        }
    }
}
