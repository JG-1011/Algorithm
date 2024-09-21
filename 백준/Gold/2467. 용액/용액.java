import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, minSum;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = new int[2];
        minSum = Integer.MAX_VALUE;

        // 두 포인터 사용
        twoPointer();

        // 결과 출력 (작은 값부터 출력)
        System.out.println(ans[0] + " " + ans[1]);
    }

    private static void twoPointer() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            // 0에 가까운 값을 찾으면 갱신
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            // 용액의 합이 음수면, 더 큰 값을 찾아야 하므로 left를 증가
            if (sum < 0) {
                left++;
            }
            // 용액의 합이 양수면, 더 작은 값을 찾아야 하므로 right를 감소
            else {
                right--;
            }
        }
    }
}
