// 프로그래머스 - 힙 - 디스크컨트롤러

import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int end = 0;
        int idx = 0;
        int cnt = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        while(cnt < jobs.length){
            while(idx < jobs.length && jobs[idx][0] <= end){
                pq.add(jobs[idx++]);
            }
            
            if(pq.isEmpty()){
                end = jobs[idx][0];
            } else {
                int[] array = pq.poll();
                answer += (array[1] + end - array[0]);
                end += array[1];
                cnt++;
            }
        }
        
        return answer / jobs.length;
    }
}
