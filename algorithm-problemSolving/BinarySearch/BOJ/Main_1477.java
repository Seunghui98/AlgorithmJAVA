// BOJ - 휴게소 세우기 (1477번)
// BinarySearch

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int l = Integer.parseInt(data[2]);
        int[] arr = new int[n+2];
        arr[0] = 0;
        arr[n+1] = l;
        String[] input = br.readLine().split(" ");
        for(int i=1;i<n+1;i++){
            arr[i] = Integer.parseInt(input[i-1]);
        }

        Arrays.sort(arr);
        int diff = 0;

        for(int i=0;i<n+1;i++){
            diff = Math.max(diff, Math.abs(arr[i]-arr[i+1]));
        }

        int left = 1;
        int right = l-1;


        while (left <= right){
            int mid = (left+right) / 2;
            int cnt = 0;

            for(int i=1;i<n+2;i++){
                cnt += (arr[i]-arr[i-1]-1) / mid;
            }

            if(cnt > m){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);



    }
}
