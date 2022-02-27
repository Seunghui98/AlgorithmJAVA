// BOJ - 딱지놀이(13300번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_13300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            int[] arr_A = new int[4];
            String[] data1 = br.readLine().split(" ");
            for(int j=1;j< data1.length;j++){
                int num = Integer.parseInt(data1[j]);
                arr_A[num-1] += 1;
            }

            int[] arr_B = new int[4];
            String[] data2 = br.readLine().split(" ");
            for(int j=1;j< data2.length;j++){
                int num = Integer.parseInt(data2[j]);
                arr_B[num-1] += 1;
            }
            boolean check = false;
            for(int j=3;j>=0;j--){
                if(arr_A[j] > arr_B[j]){
                    System.out.println("A");
                    check = true;
                    break;
                } else if (arr_A[j] < arr_B[j]){
                    System.out.println("B");
                    check = true;
                    break;
                }
            }
            if(!check){
                System.out.println("D");
            }

        }

    }
}
