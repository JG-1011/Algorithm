import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 구간 합 계산 (첫 번째 K개의 합)
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        int maxSum = sum;

        // 슬라이딩 윈도우 방식으로 최대 합 계산
        for (int i = K; i < N; i++) {
            sum = sum - arr[i - K] + arr[i]; // 앞의 값을 빼고 새로운 값을 추가
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}
