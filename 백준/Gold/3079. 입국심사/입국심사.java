import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 심사대 수
        int m = Integer.parseInt(st.nextToken()); // 사람 수

        int[] times = new int[n];
        long maxTime = 0;

        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        // 이분 탐색 범위: 최소 1 ~ 최대 maxTime * m
        long left = 1;
        long right = maxTime * (long) m;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long total = 0;
            for (int time : times) {
                total += mid / time; // mid 시간 동안 각 심사관이 처리할 수 있는 사람 수
                if (total >= m) break; // 최적화: m명 넘으면 break
            }

            if (total >= m) {
                answer = mid;       // 가능한 최소 시간 후보
                right = mid - 1;    // 더 짧은 시간도 가능한지 확인
            } else {
                left = mid + 1;     // 시간이 부족하므로 늘리기
            }
        }

        System.out.println(answer);
    }
}