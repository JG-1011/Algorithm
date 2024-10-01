import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 주사위를 굴린다
 * 나온 수가 4라면 i + 4번 컨으로 이동해야 한다.
 * 만약 굴린 결과가 100번 칸을 넘어간다면 이동할 수 없다
 * 도착한 칸이 사다리면, 사다리를 타고 올라간다.
 * 뱀이 있는 칸에 도착하면 뱀을 따라서 내려가게 된다.
 *
 * 목표는 1번에서 100번에 도착하는 것
 * 100번에 도착하기 위해 굴려야 하는 주사위 횟수의 최소값은?
 *
 * 해결
 * bfs를 이용해서 풀이
 *
 *
 *
 *
 *
 *
 * */
public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[101];

        for (int i = 0; i < 101; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start] = end;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start] = end;
        }

        System.out.println(bfs(1));


    }

    private static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[101];

        queue.add(start);
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int dice = 1; dice <= 6; dice++) {
                int next = now + dice;

                if (next > 100) {
                    continue;
                }

                next = arr[next];

                // next != 1 : 시작노드를 다시 가는 것을 방지하기 위한 처리(무한루프방지)
                if (visited[next] == 0 && next != 1) { 
                    visited[next] = visited[now] + 1;
                    queue.add(next);

                    if (next == 100) {
                        return visited[100];
                    }
                }
            }
        }

        return visited[100];
    }
}