import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 뒤에서부터 해결?
 * 현재 상담 포함 가능한지 말지 판단 가능할듯?
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] - 1 <= N) { // 완료o
                dp[i] = Math.max(dp[i + 1], P[i] + dp[i + T[i]]);
            } else { // 완료x
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
    }
}
