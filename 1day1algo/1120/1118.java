// 프로그래머스 - H-Index
// https://school.programmers.co.kr/learn/courses/30/lessons/42747
// 정렬
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int max_value = 0;
        for(int i=0;i<citations.length;i++){
            if((citations.length-i) <= citations[i] ){
                max_value = citations[i];
                break;
            }
        }
        return max_value;
    }
}
