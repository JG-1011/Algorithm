/*
i는 가로길이
*/
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        
        for(int i = 3; i <= brown; i++) {
            int r = (brown - (i * 2)) / 2;
            int brownCnt = ( 2 * i ) + ( 2 * r );
            
            // 브라운 수가 같지 않거나 && 세로길이가 가로보다 길면 패스
            if(brownCnt != brown || i < r + 2) continue;
            
            int yellowCnt = (i - 2) * r;
            
            if(yellowCnt != yellow) continue;
            
            answer[0] = i;
            answer[1] = r + 2;
        }
        
        return answer;
    }
}