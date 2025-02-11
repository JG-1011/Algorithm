import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 숨바꼭질을 하고 있음
 * 수빈이는 N에 있고 동생은 K에 있음
 * 수빈이가 걷거나 순간이동을 통해 동생을 잡는다고 했을 때 얼마나 걸리는지 구해
 */
public class Main {
    static int N, K;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[100001];

        Arrays.fill(dist, -1);

        System.out.println(bfs(N, K));
    }

    private static int bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == end) return dist[now];

            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (next >= 0 && next <= 100000 && dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    queue.add(next);
                }
            }
        }

        return -1;
    }
}