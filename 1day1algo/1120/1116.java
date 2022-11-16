// 프로그래머스 - 가장큰수
// https://school.programmers.co.kr/learn/courses/30/lessons/42746
import java.util.*;

class Solution {
    public class Node implements Comparable<Node> {
        String str;
        
        public Node(int num, String str){
            this.str = str;
        }
        
        @Override
        public int compareTo(Node o) {
            return -(Integer.parseInt(this.str + o.str) - Integer.parseInt(o.str + this.str));
        }
    }
    public String solution(int[] numbers) {
        String answer = "";
        ArrayList<Node> list = new ArrayList<>();
        for(int n:numbers){
            list.add(new Node(n, String.valueOf(n)));
        }
        
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            if(i == 0){
                if(list.get(i).str.equals("0")){
                    return "0";
                } 
            }
            answer += list.get(i).str;
        }

        return answer;
    }
}
