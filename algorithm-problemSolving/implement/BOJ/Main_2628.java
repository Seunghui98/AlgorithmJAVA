// BOJ - 종이 자르기(2628번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_2628 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int w = Integer.parseInt(data[0]);
        int h = Integer.parseInt(data[1]);

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> width = new PriorityQueue<>();
        PriorityQueue<Integer> height = new PriorityQueue<>();
        width.add(0);
        width.add(h);
        height.add(0);
        height.add(w);

        for(int i=0;i<n;i++){
            String[] input = br.readLine().split(" ");
            if(input[0].equals("0")){
                // 가로
                width.add(Integer.parseInt(input[1]));
            } else{
                // 세로
                height.add(Integer.parseInt(input[1]));
            }
        }

        int wid_m = 0, temp_w = 0, hei_m = 0, temp_h = 0;
        while (width.size() > 1){
            int start = width.poll();
            int end = width.peek();
            temp_w = end - start;
            if(temp_w > wid_m) wid_m = temp_w;
        }

        while(height.size() > 1){
            int start = height.poll();
            int end = height.peek();
            temp_h = end - start;
            if(temp_h > hei_m) hei_m = temp_h;
        }

        System.out.println(hei_m*wid_m);
    }
}
