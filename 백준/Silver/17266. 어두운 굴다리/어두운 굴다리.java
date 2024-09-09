import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결방법
 * 높이를 하나하나 확인하는 완탐으로 하면 시간초과 발생!!
 * 그래서 이분탐색을 통해 logN 탐색
 *
 * */
public class Main {

    static int[] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[M];

        // map에 가로등 위치를 담는다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1; // 굴다리 최소 크기
        int right = N;
        int ans = 0;

        // 이분탐색 시작
        while (left <= right) {
            int mid = (left + right) / 2;

            if (canlight(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    // 불빛이 굴다리 전체를 비출 수 있는지 확인하는 메서드
    // mid는 가로등이 비출 수 있는 거리
    private static boolean canlight(int mid) {
        int prev = 0; // 이전 가로등이 비춘 마지막 위치

        // 
        for (int i = 0; i < map.length; i++) {
            if (map[i] - mid <= prev) { // 가로등이 비출 수 있는 왼쪽 끝
                prev = map[i] + mid; // 왼쪽이 만족하면 똑같이 오른쪽도 비추니까 prev 업데이트
            } else {
                return false;
            }
        }
        return N - prev <= 0; // 마지막 가로등이 굴다리 끝을 비추는지 확인
    }
}