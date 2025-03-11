import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제
 * N개의 빌딩이 있다.
 * 빌딩의 키가 나와있고 관리사는 오른쪽만 볼 수 있대
 * 또 자기보다 높거나 같은 빌딩은 못봐
 *
 * 오른쪽을 바라보니까 뒤쪽부터 시작해보자
 * 10 3 7 4 12 2
 *
 * Stack : 2
 *
 * 2
 * 비어있으면 0 넣고
 * push 2
 *
 * 12
 * 비어있지않고 새로운 빌딩이 더 크면
 * 클때까지 빼 > 이때 카운트
 * 1
 * push 12
 *
 * 4
 * 비어있지않고 새로운 빌딩이 작으면 0 넣고
 * push 4
 *
 * 7
 * 비어있지 않고 새로운 빌딩이 더 크면
 * 클때까지 빼 > 이떄 카운트
 * 12앞에서 멈춰
 * push 7
 *
 * 3
 * 0
 * push 3
 * 새로운 빌딩이 크네 3 7 12
 *
 * 10일때 4가 없음
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;

        if (N == 1) {
            ans = 0;
        } else {
            int left = 0;
            int right = 0;
            int cnt = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                left = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    right = arr[j];
                    if (left <= right) break;

                    cnt++;
                }
                ans += cnt;
                cnt = 0;
            }
        }

        System.out.println(ans);
    }
}
