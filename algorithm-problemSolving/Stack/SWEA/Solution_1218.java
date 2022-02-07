// SWEA - 괄호 짝짓기(1218)
// 스택
import java.io.*;
import java.util.Stack;

public class Solution_1218 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = 10;
        for(int test_case=1;test_case<=T;test_case++){
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split("");

            Stack<String> stack = new Stack<>();
            boolean check = false;
            for(int i=0;i<str.length;i++){
                if(str[i].equals("(") || str[i].equals("{") || str[i].equals("<") || str[i].equals("[")){
                    stack.push(str[i]);
                } else {
                    if(!stack.isEmpty()){

                             if(str[i].equals(")")){
                                        if(stack.peek().equals("(")){
                                            stack.pop();
                                        } else {
                                            check = true;
                                            break;
                                        }
                                    } else if(str[i].equals("}")){
                                        if(stack.peek().equals("{")){
                                            stack.pop();
                                        } else {
                                            check = true;
                                            break;
                                        }
                                    } else if(str[i].equals(">")){
                                        if(stack.peek().equals("<")){
                                            stack.pop();
                                        } else {
                                            check = true;
                                            break;
                                        }
                                    } else if(str[i].equals("]")){
                                        if(stack.peek().equals("[")){
                                            stack.pop();
                                        } else {
                                            check= true;
                                    break;
                                }
                            }


                    } else {
                        check = true;
                        break;
                    }
                }

            }

            if(!stack.isEmpty()){
                check = true;
            }

            if(check){
                bw.write(String.valueOf("#"+test_case+" 0\n"));
            } else{
                bw.write(String.valueOf("#"+test_case+" 1\n"));
            }

        }

        bw.flush();
        bw.close();
    }
}
