import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * hashMap을 사용해야 할 거 같은데?
 * 두값을 저장하고 결과값을 넣는다?
 * Hash<결과값, 결과값을 만든 두 값>
 * 정렬하고 > 이게 문제네
 *
 * 두개를 더한 배열을 만들고 > 정렬 > 0에 가까운 수를 찾았다고 가정
 * 그럼 그 결과값을 찾기 무언가를 한다? 너무 비효율적 아닌가?
 *
 *
 *
 * */
public class Main {

    static int[] arr;
    static int N, max;
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
        max = Integer.MAX_VALUE;
        binarySearch();

        System.out.println(ans[0] + " " + ans[1]);

    }

    private static void binarySearch() {
        for (int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                int sum = arr[i] + arr[mid];

                if (Math.abs(sum) < max) {
                    ans[0] = arr[i];
                    ans[1] = arr[mid];
                    max = Math.abs(sum);
                }

                if (sum < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }

        }
    }

}