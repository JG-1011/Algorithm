import java.io.*;

public class Main {
    static long[] dp = new long[101]; // 최대 100까지 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        // DP 초기값 설정
        dp[1] = dp[2] = dp[3] = 1;

        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}
