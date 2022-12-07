// BOJ - 수들의 합4(2015번)
// 누적합 + HashMap
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] sum = new int[n+1];
        HashMap<Integer, Integer> hash = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        long answer = 0;
        for(int i=1;i<n+1;i++){
            int num = Integer.parseInt(st.nextToken());
            sum[i] = num + sum[i-1];
            if(sum[i] == m) answer++;
            answer += hash.getOrDefault(sum[i]-m, 0);
            if(hash.containsKey(sum[i])){
                hash.put(sum[i], hash.get(sum[i])+1);
            } else {
                hash.put(sum[i], 1);
            }
        }

        System.out.println(answer);

    }
}
