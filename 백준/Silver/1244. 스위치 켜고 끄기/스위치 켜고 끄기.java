import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1 : 켜져있음
 * 0 : 꺼져있음
 *
 * 성별에 따라 스위치를 조작한다
 * 남 : 수의 배수를 바꾼다
 * 여 : 대칭으로 맞는 곳까지 찾아서 바꾼다 (자기 자신도 바꾼다)
 *
 * 스위치 개수
 * 스위치 상태
 * 학생수
 * 성별, 받은 수 (남자1, 여자2)
 *
 * 한 줄에 20개씩 출력 21번째는 둘째 줄에 출력
 *
 * 배열에 담아서 써?
 * 인덱스
 * 스위치 상태
 * */
public class Main {
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int student = Integer.parseInt(br.readLine());

        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            switch (sex) {
                case 1:
                    int cnt = N / num;

                    for (int j = 1; j <= cnt; j++) {
                        change(num * j);
                    }

                    break;

                case 2:
                    change(num);

                    int left = num - 1;
                    int right = num + 1;

                    // 범위 안이고 대칭이면 바꿔
                    while (left >= 1 && left <= N && right >= 1 && right <= N && arr[left] == arr[right]) {
                        change(left--);
                        change(right++);
                    }
            }
        }

        if (N > 20) {
            for (int i = 1; i <= N; i++) {
                sb.append(arr[i]).append(" ");
                if (i % 20 == 0) sb.append("\n");
            }
        } else {
            for (int i = 1; i <= N; i++) {
                sb.append(arr[i]).append(" ");
            }
        }

        System.out.println(sb);
    }

    public static void change(int index) {
        if (arr[index] == 0) arr[index] = 1;
        else arr[index] = 0;
    }
}