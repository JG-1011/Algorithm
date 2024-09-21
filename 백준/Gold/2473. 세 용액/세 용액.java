import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 해결방법
 * for문으로 i를 하나 잡고 나머지 두개를 투포인터로 돌리면서 더하면서 진행해보자
 * 아니면
 * 두개를 더한 배열을 만들고 나머지 하나를 이분탐색으로 돌리면서 0에 가까운 수를 찾는다
 *
 * 출력값을 오름차순으로 정렬해서 출력하라고했는데 안함;; 그래서 두번 틀림
 * 
 * */
public class Main {

    static int N;
    static long min;
    static long[] arr, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        ans = new long[3];
        min = Long.MAX_VALUE;
        TwoPointer();

        Arrays.sort(ans);

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);

    }

    private static void TwoPointer() {
        for (int i = 0; i < N - 2; i++) { // left와 right 자리를 남겨놔야지 그래서 N-2까지
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < min) {
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];

                    min = Math.abs(sum);
                }

                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }

        }
    }
}