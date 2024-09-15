import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 해결방법
 * 중복불가라는 말이 없는 것을 보아 중복도 뽑힐 수 있나보네?
 * 2 2 3 5 10 18 이런식으로?
 *
 * a + b + c = k라면
 * a와 b를 더해 (이중포문으로) 그리고 c를 넘기면
 * a + b = k - c
 * a+b 배열 안에 K-c가 있는지 이분탐색을 한다...
 * 이걸 어떻게 생각해내냐
 *
 * 1.두개의 합 배열 크기는 어떻게?? 리스트? > 정렬
 *
 * */
public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        List<Integer> twoSum = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int x = arr[i] + arr[j];
                twoSum.add(x);
            }
        }

        Collections.sort(twoSum);

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int target = arr[i] - arr[j];
                if (Collections.binarySearch(twoSum, target) >= 0) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }

    }

}