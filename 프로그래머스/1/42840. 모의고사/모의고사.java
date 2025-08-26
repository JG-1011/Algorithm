import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] st1 = {1, 2, 3, 4, 5}; // 5
        int[] st2 = {2, 1, 2, 3, 2, 4, 2, 5}; // 8
        int[] st3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; //10

        int[] scores = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == st1[i % 5]) scores[0]++;
            if(answers[i] == st2[i % 8]) scores[1]++;
            if(answers[i] == st3[i % 10]) scores[2]++;
        }
        
        int maxScore = 0;
        for (int i = 0; i < 3; i++) {
            maxScore = Math.max(maxScore, scores[i]);
        }

        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            if(maxScore == scores[i]) list.add(i + 1);
        }
        
        //Collections.sort(list); //1번부터 차례대로 들어가니까 정렬 필요x
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}