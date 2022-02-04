// SWEA - Flatten(1208번)
// 구현(정렬)
import java.io.*;
import java.util.Arrays;

public class Solution_1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int t=1;t<=10;t++){
            int dump = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");

            int[] box = new int[s.length];


            for(int i=0;i<s.length;i++){
                box[i] = Integer.parseInt(s[i]);
            }
            Arrays.sort(box);

            int cnt = 1;
            while (cnt <= dump){
                if((box[s.length-1] - box[0]) <= 1){
                    break;
                }

                if(box[s.length-1] >= 2){
                    box[s.length-1] -= 1;
                    box[0] += 1;
                }
                cnt++;
                Arrays.sort(box);
            }
            int diff = box[s.length-1]-box[0];
            bw.write(String.valueOf("#"+t+" "+diff));
            bw.write(String.valueOf("\n"));
        }


        bw.flush();
        bw.close();
    }


}
