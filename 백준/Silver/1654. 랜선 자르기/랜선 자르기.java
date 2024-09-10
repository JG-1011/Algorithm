import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 이분탐색
 *
 * */
public class Main {
    static long[] lan;
    static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lan = new long[K];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            int x = Integer.parseInt(br.readLine());
            lan[i] = x;
            max = Math.max(max, x);
        }

        long left = 1;
        long right = max;
        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canMake(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);

    }

    private static boolean canMake(long length) {
        long cnt = 0;
        for (long x : lan) {
            cnt += x / length;
        }

        return cnt >= N;
    }
}