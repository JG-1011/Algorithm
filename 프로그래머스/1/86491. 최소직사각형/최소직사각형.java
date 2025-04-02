/*
다 만족하는 최소값을 구해야 한다.
바꿔서 체크를 해서 만족하는지 해보자

문제점
넓이가 작은 지갑을 골랐을 때 내가 고른 가로,세로 길이를 어떻게 구별하지?
*/
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxR = 0; // 세로
        int maxC = 0; // 가로
        
        for (int[] arr : sizes) {
            int r = arr[1];
            int c = arr[0];
            
            int tempR = maxR;
            int tempC = maxC;
            // 다음 세로길이와 최대 세로길이 중 긴걸 뽑아
            maxR = Math.max(tempR,r);
            // 다음 가로길이와 최대 가로길이 중 긴걸 뽑아
            maxC = Math.max(tempC,c);
            // 두개 곱해서 작은 수를 뽑아
            answer = maxR * maxC;
            
            // 가로세로를 바꿔서 곱해본다
            // 그리고 넓이가 더 작은 것을 골라낸다
            if (answer > Math.max(tempR,c) * Math.max(tempC,r)) {
                maxR = Math.max(tempR,c);
                maxC = Math.max(tempC,r);
                answer = maxR * maxC;
            }
        }
        
        return answer;
    }
}