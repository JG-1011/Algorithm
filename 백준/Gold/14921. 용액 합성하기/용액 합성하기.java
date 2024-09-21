import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 투포인트로 양 끝에서 하나씩 움직이면서 최대한 0에 가까운 값을 만들면 될 듯
 *
 * */
public class Main {

    static int N, ans, min;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        min = Integer.MAX_VALUE;
        TwoPointer();

        System.out.println(ans);

    }

    private static void TwoPointer() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                ans = sum;
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