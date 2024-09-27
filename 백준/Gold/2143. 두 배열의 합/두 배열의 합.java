import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long T, cnt;
    static int n, m;
    static int[] A, B;

    static List<Long> sumA;
    static List<Long> sumB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Long.parseLong(br.readLine());

        // 배열 A 입력
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 B 입력
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        sumA = new ArrayList<>();
        sumB = new ArrayList<>();

        // 배열 A의 부분합 리스트 생성
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        // 배열 B의 부분합 리스트 생성
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }

        // 두 리스트 정렬
        Collections.sort(sumA);
        Collections.sort(sumB);

        cnt = 0;

        // sumA의 각 값에 대해 T - sumA.get(i)를 sumB에서 이진 탐색
        for (Long aLong : sumA) {
            long target = T - aLong;
            cnt += countOccurrences(sumB, target);
        }

        // 결과 출력
        System.out.println(cnt);
    }

    // sumB에서 target 값이 몇 번 등장하는지 계산하는 함수
    private static int countOccurrences(List<Long> list, long target) {
        int lowerBound = findLowerBound(list, target);
        int upperBound = findUpperBound(list, target);
        return upperBound - lowerBound;  // 등장 횟수 반환
    }

    // lowerBound: target 이상의 값이 처음으로 등장하는 위치를 찾음
    private static int findLowerBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // upperBound: target을 초과하는 값이 처음으로 등장하는 위치를 찾음
    private static int findUpperBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}