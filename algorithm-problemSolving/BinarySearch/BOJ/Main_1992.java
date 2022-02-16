// BOJ - 쿼드 트리(1992번)
// 이진 탐색(귀납)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1992 {
    public static char[][] map;
    public static int n;
    public static ArrayList<Character> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for(int i=0;i<n;i++){
            String data = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = data.charAt(j);
            }
        }
        ans = new ArrayList<>();
        boolean ch = false;
        if(!check(0,0, n, n)){
            ans.add('(');
            ch = true;
        }
        binary_search(0, 0, n);
        if(ch){
           ans.add(')');
        }
        for(char c :ans){
            System.out.print(c);
        }
    }

    public static void binary_search(int x, int y, int len){

        if(check(x, y, x+len, y+len)){
            ans.add(map[x][y]);
            return;
        } else {

            if (check(x, y, x + len / 2, y + len / 2)) {
                ans.add(map[x][y]);
            } else {
                ans.add('(');
                binary_search(x, y, len / 2);
                ans.add(')');
            }

            if (check(x, y + len / 2, x + len / 2, y + len)) {
                ans.add(map[x][y + len / 2]);
            } else {
                ans.add('(');
                binary_search(x, y + len / 2, len / 2);
                ans.add(')');
            }

            if (check(x + len / 2, y, x + len, y + len / 2)) {
                ans.add(map[x + len / 2][y]);
            } else {
                ans.add('(');
                binary_search(x + len / 2, y, len / 2);
                ans.add(')');
            }

            if (check(x + len / 2, y + len / 2, x + len, y + len)) {
                ans.add(map[x + len / 2][y + len / 2]);
            } else {
                ans.add('(');
                binary_search(x + len / 2, y + len / 2, len / 2);
                ans.add(')');
            }


        }


    }

    public static boolean check(int s_x, int s_y, int e_x, int e_y){
        char c = map[s_x][s_y];
        for(int i=s_x;i<e_x;i++){
            for(int j=s_y;j<e_y;j++){
                if(map[i][j] != c){
                    return false;
                }
            }
        }
        return true;
    }
}
