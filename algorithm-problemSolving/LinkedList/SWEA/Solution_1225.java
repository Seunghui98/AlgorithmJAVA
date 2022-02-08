// SWEA - 암호 생성기1(1225)
// Queue
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int test_case=1;test_case<=10;test_case++){
            int n = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(" ");

            Queue<Integer> queue = new LinkedList<>();
            for(int i=0;i<data.length;i++){
                queue.offer(Integer.parseInt(data[i]));
            }


            outer:while(true){
                for(int i=1;i<=5;i++){
                    int num = queue.poll();
                    if(num-i <= 0){
                        queue.offer(0);
                        break outer;
                    } else {
                        queue.offer(num-i);
                    }
                }

            }


            System.out.print("#"+test_case+" ");
            for(int i=0;i<8;i++){
                System.out.print(queue.poll()+" ");
            }
            System.out.println();
        }
    }
}
