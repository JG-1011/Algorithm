import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 해결방법
 * 집합A에는 속하지만 B에는 속하지 않는 원소를 찾아라
 * */
public class Main {
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());

        A = new int[nA];
        B = new int[nB];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nA; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int left = 0;
        int right = B.length - 1;
        int cnt = 0;

        for (int i = 0; i < nA; i++) {
            int target = A[i];

            if (binarySearch(target)) {
                continue;
            }

            cnt++;
            sb.append(target).append(" ");
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    private static boolean binarySearch(int target) {
        int left = 0;
        int right = B.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (B[mid] == target) {
                return true;
            } else if (B[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}