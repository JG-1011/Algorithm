/*
n개의 정수를 +,-를 통해서 target을 만들어라
dfs로 +,-를 골라서 가능하면 카운트
*/
class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
         
        return answer;
    }
    
    public static void dfs(int[] numbers, int target, int sum, int depth) {
        if(depth == numbers.length) {
            if(sum == target) answer++;
            return;
        }
    
        for (int num : new int[]{1,-1}) {
            if(num == 1) {
                dfs(numbers, target, sum + numbers[depth], depth +1);
            }else {
                dfs(numbers, target, sum - numbers[depth], depth + 1);
            }
        }
    }
}