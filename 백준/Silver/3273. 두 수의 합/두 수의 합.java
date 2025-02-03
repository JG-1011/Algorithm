import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine()); // 목표 합

        // 1. 배열 정렬 (O(N log N))
        Arrays.sort(arr);

        // 2. 투 포인터 탐색 (O(N))
        int left = 0, right = N - 1;
        int count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == x) {  // 합이 x면 카운트 증가
                count++;
                left++;  // 다음 탐색을 위해 포인터 이동
                right--;
            } else if (sum < x) {  // 합이 작으면 left 증가
                left++;
            } else {  // 합이 크면 right 감소
                right--;
            }
        }

        System.out.println(count);
    }
}
