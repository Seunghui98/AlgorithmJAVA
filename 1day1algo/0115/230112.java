// BOJ - 단어 수학(1339)
// 그리디 알고리즘

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main_1339 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] alpha = new long[26];
        for(int i=0;i<n;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                alpha[str.charAt(j) - 'A'] += Math.pow(10, str.length()-j-1);
            }
        }

        Arrays.sort(alpha);
        int num = 9;
        long answer = 0;
        for(int i=25;i>=0;i--){
            if(alpha[i] == 0) continue;
            String s = String.valueOf(alpha[i]);
            char[] c = s.toCharArray();
            for(int j=0;j<c.length;j++){
                if(c[j] == '0') continue;
                answer += (c[j]-'0') * num * Math.pow(10, c.length-j-1);
            }
            num--;
        }

        System.out.println(answer);


    }
}
