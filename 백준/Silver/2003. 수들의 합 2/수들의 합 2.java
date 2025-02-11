import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 배열 i~j 연속된 수들을 더해서 M이 되는 경우의 수를 구해라
 *
 * 해결
 * 이분탐색이나 투포인트로 해결하면 될 듯
 * */
public class Main {
    static int[] arr;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPoint());

    }

    private static int twoPoint() {
        int left = 0;
        int right = 0;
        int sum = 0;
        int cnt = 0;

        while (right < N) {
            sum += arr[right];

            while (sum > M) {
                sum -= arr[left];
                left++;
            }

            if (sum == M) cnt++;

            right++;
        }

        return cnt;
    }
}
