/*
순서대로, n개 퍼즐을, 제한 시간 내에, 풀어야 함
diff(난이도), time_cur(소요시간), time_prev(이전퍼즐 소요시간)
diff <= level 이면 time_cur만큼만 시간 사용
diff > level 이면 (diff - level) * (time_cur + time_prev) 이후 time_cur 만큼 시간 이용

이분탐색으로 찾아보면 될 거 같아
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = getMax(diffs);
        int answer = right; //나의 레벨이 diffs에서 제일 큰 값과 같으면 통과할 수 있으니까
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int prev = 0;
            long time = 0;
            
            for (int i = 0; i < diffs.length; i++) {
                if (diffs[i] <= mid) {
                    time += times[i];
                } else {
                    time += (diffs[i] - mid) * (times[i] + prev) + times[i];
                }
                
                prev = times[i];
            }
            
            if (time <= limit) { // 조건 만족하면
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public static int getMax(int[] diffs) {
        int max = 0;
        
        for (int diff : diffs) max = Math.max(max, diff);
        
        return max;
    }
}