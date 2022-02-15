// 정보올림피아드 - 냉장고(1828)
// 그리디
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main_1828_이승희 {
    public static class Chemi implements Comparable<Chemi> {
        int min;
        int max;
        public Chemi(int min, int max){
            this.min = min;
            this.max = max;
        }


        @Override
        public int compareTo(Chemi o) {
            return (this.max != o.max?this.max-o.max:this.min-o.min);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Chemi[] arr = new Chemi[n];
        for(int i=0;i<n;i++){
            arr[i] = new Chemi(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(arr);
        int cnt = 1;
        int max = arr[0].max;
        for(int i=1;i<n;i++){
            if(max < arr[i].min){
                cnt++;
                max = arr[i].max;
            }
        }

        System.out.println(cnt);
    }
}
