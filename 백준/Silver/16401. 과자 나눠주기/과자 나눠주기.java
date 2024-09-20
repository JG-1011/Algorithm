import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 과자 최대 길이 찾아서 그걸로 이분탐색 시작
 *
 * */
public class Main {

    static int M, N, ans, max;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }

        ans = 0;
        BinarySearch();

        System.out.println(ans);

    }

    private static void BinarySearch() {
        int left = 1;
        int right = max;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 오버플로우 방지

            if (CanShare(mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
    }

    private static boolean CanShare(int value) {
        int cnt = 0;

        for (int x : arr) {
            cnt += x / value;
        }

        return cnt >= M;
    }

}