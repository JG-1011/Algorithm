import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());

        // DP 배열 생성
        int maxN = 10000; // 문제에서 주어진 최대 값
        int[] dp = new int[maxN + 1];
        dp[0] = 1; // 0을 만드는 방법은 1가지 (아무것도 사용하지 않음)

        // 1, 2, 3으로 조합 계산
        for (int num = 1; num <= 3; num++) {
            for (int i = num; i <= maxN; i++) {
                dp[i] += dp[i - num];
            }
        }

        // 각 테스트 케이스 처리
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        // 결과 출력
        System.out.print(sb);
    }
}
