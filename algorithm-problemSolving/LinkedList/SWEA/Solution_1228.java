// SWEA - 암호문1(1228번)
// LinkedList
import java.util.LinkedList;
import java.util.Scanner;

public class Solution_1228 {

    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        for(int test_case=1;test_case<=10;test_case++){
            int n = scan.nextInt();
            LinkedList<Integer> list = new LinkedList<>();

            for(int i=0;i<n;i++){
                list.add(scan.nextInt());
            }

            int d = scan.nextInt();

            for(int i=0;i<d;i++){
                scan.next();

                int x = scan.nextInt();
                int y = scan.nextInt();
                int[] arr = new int[y];
                for(int j=0;j<y;j++){
                    arr[j] = scan.nextInt();
                }

                for(int j=arr.length-1;j>=0;j--){
                    list.add(x, arr[j]);
                }

            }

            System.out.print("#"+test_case+" ");
            for(int i=0;i<10;i++){
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
        }

    }
}
