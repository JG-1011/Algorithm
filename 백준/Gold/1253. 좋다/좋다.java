import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 문제를 보면 Ai가 N개 주어지는데 범위를 보면
 * |Ai| ≤ 1,000,000,000, Ai는 정수 >> 음수도 있다는 뜻
 *
 * 음수 생각안하고 코드 작성해서 틀림
 *
 * 이것도 틀림 다시 해보자
 *
 *
 * 인사이트
 * 투포인트 할 때 자기 자신 넘기는 방법 배움
 *
 * */
public class Main {
    static int N, cnt;
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

        Arrays.sort(arr);
        cnt = 0;

        for (int i = 0; i < N; i++) {
            if (isGoodNumber(i)) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    private static boolean isGoodNumber(int index) {
        long target = arr[index];
        int left = 0;
        int right = N - 1;

        while (left < right) {
            if (left == index) {
                left++;
            } else if (right == index) {
                right--;
            } else {
                long sum = arr[left] + arr[right];

                if (sum == target) {
                    return true;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return false;
    }

}