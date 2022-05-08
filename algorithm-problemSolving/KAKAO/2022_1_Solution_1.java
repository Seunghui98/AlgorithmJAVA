// 2022 상반기 카카오 인턴 1번
import java.util.HashMap;

public class 2022_1_Solution_1 {
    public static void main(String[] args) {
        String ans = solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5});
        System.out.println(ans);
    }

    public static String solution(String[] survey, int[] choices) {
        String answer = "";

        HashMap<Character, Integer> hash = new HashMap<>();

        hash.put('R', 0);
        hash.put('T', 0);
        hash.put('C', 0);
        hash.put('F', 0);
        hash.put('J', 0);
        hash.put('M', 0);
        hash.put('A', 0);
        hash.put('N', 0);



        for(int i=0;i<survey.length;i++){
            char first = survey[i].charAt(0);
            char secon = survey[i].charAt(1);

            if(choices[i] <= 3){
                hash.put(first, hash.get(first)+4-choices[i]);
            } else if(choices[i] >= 5){
                hash.put(secon, hash.get(secon)+choices[i]-4);
            }



        }



        if(hash.get('R') > hash.get('T')){
            answer += "R";
        } else if(hash.get('R') < hash.get('T')){
            answer += "T";
        } else {
            answer += "R";
        }

        if(hash.get('C') > hash.get('F')){
            answer += "C";
        } else if(hash.get('C') < hash.get('F')){
            answer += "F";
        } else {
            answer += "C";
        }

        if(hash.get('J') > hash.get('M')){
            answer += "J";
        } else if(hash.get('J') < hash.get('M')){
            answer += "M";
        } else {
            answer += "J";
        }

        if(hash.get('A') > hash.get('N')){
            answer += "A";
        } else if(hash.get('A') < hash.get('N')){
            answer += "N";
        } else {
            answer += "A";
        }




        return answer;
    }
}
