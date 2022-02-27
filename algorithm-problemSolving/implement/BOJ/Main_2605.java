// BOJ - 줄세우기(2605번)
// 구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_2605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] data = br.readLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            int num = Integer.parseInt(data[i]);
            if (num==0){
                list.add(i+1);
            } else {
                list.add(list.size()-num, i+1);
            }
        }

        for(int i=0;i<n;i++){
            System.out.print(list.get(i)+" ");
        }

    }
}
