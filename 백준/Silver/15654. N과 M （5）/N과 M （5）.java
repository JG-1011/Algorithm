import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제
 * 조합, 중복x
 *
 * */
public class Main {
    static int N, M;
    static int[] arr, arr2;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        arr2 = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        combi(0);

        System.out.println(sb);
    }

    private static void combi(int depth) {
        if (depth == M) {
            for (int num : arr2) sb.append(num).append(" ");
            sb.append("\n");
            return;

        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr2[depth] = arr[i];

                combi(depth + 1);

                visited[i] = false;
            }
        }
    }
}