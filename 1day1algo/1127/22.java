// 프로그래머스 - 카펫(42842번)
// 완전 탐색
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        int b = brown/2 +2;
        for(int i=1;i<=b;i++){
            int j = b-i;
            if(i*j == sum){
                answer[0] = i;
                answer[1] = j;
            }
        }
        
        return answer;
    }
}
