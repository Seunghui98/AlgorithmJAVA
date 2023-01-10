// 프로그래머스 - 입국심사
// 이분탐색

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = 1;
        long right = (long)n * times[times.length-1];
    
        while(left <= right){
            long mid = (left+right) /2;
            long cnt = 0;
            for(int i=0;i<times.length;i++){
                cnt += (mid/times[i]);
            }
            
            if(cnt >= n){
                answer = mid; 
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
