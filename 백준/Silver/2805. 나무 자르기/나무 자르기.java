import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int h;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tree = new int[n];

        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tree);
        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int left = 0;
        int right = tree[n - 1];

        while (left <= right) {
            int mid = (left + right) / 2;
            long current = 0;

            for (int i = 0; i < n; i++) {
                int temp = 0;

                if (tree[i] - mid >= 0) {
                    temp = tree[i] - mid;
                }

                current += temp;
            }

            if (current < m)
                right = mid - 1;
            else if (current > m)
                left = mid + 1;
            else
                return mid;

        }
        return right;
    }
}