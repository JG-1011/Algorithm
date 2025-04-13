import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        
        queue.add(begin);
        
        int answer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                String now = queue.poll();
                
                if (now.equals(target)) return answer;
                
                for (int i = 0; i < words.length; i++) {
                    if (!visited[i] && inConvert(now, words[i])) {
                        visited[i] = true;
                        queue.add(words[i]);
                    }
                }
            }
            answer++; 
        }
        
        return 0;
    }
    
    public static boolean inConvert(String a, String b) {
        int diff = 0;
        
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        
        return diff == 1;
    }
}