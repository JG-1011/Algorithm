import java.util.*;
/*
출발지부터 distance 떨어진 곳에 도착지점 있음
그 사이에 바위가 있는데 몇개를 지우려고 한다
2 11 14 17 21
거리의 최소값 중에 가장 큰 값을 return해라

distance만큼 다 검사해봐 > 완탐
근데 시간초과 발생하니까 이분탐색 활용해서 줄여보자데 시간초과 발생하니까 이분탐색 활용해서 줄여보자
바위제거를 어떻게 할것인가? > 조합으로 바위를 뽑고 이분탐색을 하면 시간이 너무 오래걸림


1 ~ 25 사이 존재
1 // 2 11 14 17 21 // 25
1 9 3 3
13부터 진행
right = 12
mid = 6
...

mid값이 나오면 이게 최소값중에 가장 큰 값이 되어야 하므로 이것보다 작으면 지워버리고
(이때 지운 개수가 n개보다 많으면 mid값을 줄이자)
크면 넘어가고 이렇게 만들어보자

*/

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        int answer = 0;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if (canCross(distance, rocks, n, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    public static boolean canCross(int distance, int[] rocks, int n, int mid) {
        int removed = 0;
        int prev = 0;
        
        for (int rock : rocks) {
            if (rock - prev < mid) {
                removed++;
                if(removed > n) return false;
            } else {
                prev = rock;
            }
        }
        
        if(distance - prev < mid) removed++;
        
        return removed <= n;
    }
}