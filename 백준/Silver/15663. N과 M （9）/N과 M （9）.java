import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * 순열 + 중복 방지
 * */
public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
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
            for (int num : list) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            // 방문하지 않았고, 이전값과 다르다면
            if (!visited[i] && arr[i] != prev) {
                visited[i] = true;
                list.add(arr[i]);
                prev = arr[i];

                combi(depth + 1);

                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}