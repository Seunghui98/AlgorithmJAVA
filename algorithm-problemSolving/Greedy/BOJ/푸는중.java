import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main_3430 {
    public static int n, m;
    public static int[] arr;
    public static int[] time;
    public static boolean[] empty;
    public static ArrayList[] rain_arr;
    public static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=TC;test_case++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[m];
            time = new int[m];
            ans = new ArrayList<>();
            rain_arr = new ArrayList[n+1];
            empty = new boolean[n+1];

            for(int i=0;i<n+1;i++){
                rain_arr[i] = new ArrayList<Integer>();
            }

            boolean check = false;

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<m;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=m-1;i>=0;i--){
                if(arr[i] > 0){
                    time[i] = arr[i];
                    rain_arr[arr[i]].add(i);
                }
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int i=1;i<n+1;i++){
                if(rain_arr[i].size() > 0){
                    pq.add((Integer)rain_arr[i].get(rain_arr[i].size()-1));
                    rain_arr[i].remove(rain_arr[i].size()-1);
                }
            }

            for(int i=0;i<m;i++){
                if(arr[i] > 0){
                    if(empty[arr[i]]){
                        check = true;
                        break;
                    }

                    empty[arr[i]] = true;
                    if(rain_arr[arr[i]].size() > 0){
                        pq.add((Integer)rain_arr[arr[i]].get(rain_arr[arr[i]].size()-1));
                        rain_arr[arr[i]].remove(rain_arr[arr[i]].size()-1);
                    }

                } else {
                    if(pq.isEmpty()){
                        ans.add(0);
                    } else {
                        int now = pq.poll();
                        ans.add(now);
                        empty[time[now]] = false;
                    }
                }
            }

            if(check){
                System.out.println("NO");
            } else {
                System.out.println("YES");
                for(int a:ans){
                    System.out.print(a+" ");
                }
                System.out.println();
            }


        }

    }
}
