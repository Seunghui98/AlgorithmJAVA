// BOJ - 로프(2217번)
// 그리디

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            int num = Integer.parseInt(br.readLine());

            arr[i] = num;
        }

        Arrays.sort(arr);

        int max = arr[n-1];
        for(int i=0;i<n;i++){
            if(arr[i] * (n-i) > max){
                max = arr[i] * (n-i);

            }
        }

        System.out.println(max);

    }
}
