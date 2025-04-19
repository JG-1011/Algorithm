import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : scoville) {
            pq.add(num);
        }
        
        while (pq.size() >= 2) {
            Integer num = pq.poll();
            
            if (num >= K) break;
            
            Integer num2 = pq.poll();
            
            pq.add(num + (num2 * 2));
            
            answer++;
        }        
        
        return (pq.poll() >= K) ? answer : -1;
    }
}