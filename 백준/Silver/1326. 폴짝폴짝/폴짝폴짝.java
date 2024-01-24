import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, a, b, ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        ans = 0;

        System.out.println(bfs(a) ? ans - 1 : -1);
    }

    private static boolean bfs(int a) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];

        queue.add(a);
        visit[a] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            while (size-- > 0) {
                int now = queue.poll();
                //꺼냈어
                //다음 값 확인해
                //값이 범위를 벗어나면 끝
                if (now == b) return true;

                for (int i = arr[now]; i <= N; i += arr[now]) {
                    int next = now + i;
                    if (next <= 0 || next > N || visit[next]) continue;
                    queue.add(next);
                    visit[next] = true;
                }

                for (int i = arr[now]; i <= N; i += arr[now]) {
                    int next = now - i;
                    if (next <= 0 || next > N || visit[next]) continue;
                    queue.add(next);
                    visit[next] = true;
                }

            }


        }

        return false;
    }
}