// BOJ - 백설 공주와 일곱 난쟁이(3040번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_3040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int sum = 0;
        for(int i=0;i<9;i++){
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
            sum += n;
        }

        outer:for(int i=0;i<9;i++){
            for(int j=i+1;j<9;j++){
                if(sum-arr[i]-arr[j] == 100){
                    for(int p=0;p<9;p++){
                        if(p == i || p == j) continue;
                        System.out.println(arr[p]);
                    }
                    break outer;
                }
            }
        }

    }
}
