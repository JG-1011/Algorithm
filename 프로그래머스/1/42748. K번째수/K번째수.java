import java.util.*;
/*
i번째부터 j번째까지 수를 잘라야 함 > copyOfRange(카피할배열, 시작인덱스, 끝 인덱스);

*/
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1];
            int k = commands[i][2] - 1;
            
            int[] arr = Arrays.copyOfRange(array, start, end);
            Arrays.sort(arr);
            answer[i] = arr[k];
        }
        
        return answer;
    }
}