// BOJ - 스위치 켜고 끄기(1244번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1244 {
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int stu_num = Integer.parseInt(br.readLine());
        for(int i=0;i<stu_num;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if(s == 1){
                xy(idx, n);

            } else {
                xx(idx-1, n);
            }
        }
        int sum = 0;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
            sum++;
            if(sum >= 20){
                sum = 0;
                System.out.println();
            }
        }

    }

    public static void xy(int number, int n){
        int start = number-1;
        while (start < n){
            arr[start] = (arr[start]==1?0:1);
            start += number;
        }
    }

    public static void xx(int number, int n){
        int left = number-1;
        int right = number+1;
        if(left < 0 || right >= n || arr[left] != arr[right]){
            arr[number] = (arr[number]==0?1:0);
            return;
        }
        while (left > -1 && right < n){
            if(arr[left] == arr[right]){
                left -= 1;
                right += 1;
            } else {
                break;
            }
        }
        left += 1;
        right -= 1;

        for(int i=left;i<=right;i++){
            arr[i] = (arr[i]==0?1:0);
        }

    }
}
