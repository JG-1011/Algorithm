import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 인사이트
 * 특정 수를 다른 수로 나눌 때, "올림 연산"을 수행하는 방법
 * cnt += (arr[i] + sub - 1) / sub; 이렇게 쓰면 아래 코드를 한 줄로 줄일 수 있다.
 *
 * if (arr[i] % sub == 0) {
 *     cnt += arr[i] / sub;
 * } else {
 *     cnt += arr[i] / sub + 1;
 * }
 * */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long cnt = N;
        st = new StringTokenizer(br.readLine());
        int totle = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= totle;

            if (arr[i] > 0) {
                cnt += (arr[i] + sub - 1) / sub;
            }
        }
        System.out.println(cnt);
    }
}