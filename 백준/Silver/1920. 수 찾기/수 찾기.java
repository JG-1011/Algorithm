import java.io.*;
import java.util.*;

public class Main {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(A);
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(target) ? "1" : "0").append("\n");
        }
        
        System.out.print(sb);
    }

    private static boolean binarySearch(int target) {
        int left = 0, right = A.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (A[mid] == target) return true;
            else if (A[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}