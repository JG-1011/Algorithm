import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 동전 개수
        int K = Integer.parseInt(st.nextToken()); // 목표 금액

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(coinChange(N, K, coins));
    }

    private static int coinChange(int N, int K, int[] coins) {
        int[] dp = new int[K + 1]; // dp[i]: i원을 만드는 경우의 수
        dp[0] = 1; // 0원을 만드는 경우는 "아무것도 선택하지 않는 경우" 1개

        for (int coin : coins) {  // **각 동전별로 dp 배열을 갱신**
            for (int j = coin; j <= K; j++) {  
                dp[j] += dp[j - coin];  
            }
        }

        return dp[K]; // K원을 만드는 경우의 수 반환
    }
}
