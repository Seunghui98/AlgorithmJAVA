// BOJ - 요세푸스 문제(1158)
// LIST 활용
import java.util.*;

public class Main_1158 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        List<Integer> data = new ArrayList<>();
        for(int i=1;i<=n;i++){
            data.add(i);
        }
        int[] ans = new int[n];
        int pointer = 0;
        for(int i=0;i<n;i++){
            pointer += (k-1);
            pointer %= data.size();
            ans[i] = data.get(pointer);
            data.remove(pointer);
        }

        System.out.print("<");
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]);
            if(i != ans.length-1){
                System.out.print(", ");
            }
        }
        System.out.print(">");
    }
}
