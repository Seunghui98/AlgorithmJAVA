// BOJ - 직사각형 네개의 합집합의 면적 구하기(2669번)
// 구현

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2669 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int max = 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int k=n;k>=0;k--){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(n);
            list.add(k);
            while (true){
                int len = list.size();
                int num1 = list.get(len-2);
                int num2 = list.get(len-1);
                if(num1 - num2 >= 0){
                    list.add(num1-num2);
                } else{
                    break;
                }
            }

            if(list.size() > max){
                ans = list;
                max = list.size();
            }

        }

        System.out.println(max);
        for(int i=0;i<max;i++){
            System.out.print(ans.get(i)+" ");
        }

    }
}
