/*
문제
비긴에서 타겟으로 최소로 변환해라

조건
1. 알파벳 하나씩 변경 가능하다
2. 중복되는 단어 없다
3. 모든 단어 길이가 같다
4. 변환할 수 없는 경우 0으로 출력해라

해결
1. 하나만 다른지 판단하는 함수 > for문으로 같은지 확인? 
2. dfs로 찾아보자
*/

class Solution {
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        
        dfs(begin, target, words, visited, 0);
        
        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }
    
    public static void dfs(String begin, String target, String[] words, boolean[] visited, int depth) {
        if (begin.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isConvert(begin, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public static boolean isConvert(String a, String b) {
        int diff = 0;
        
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        
        return diff == 1;
    }
}
