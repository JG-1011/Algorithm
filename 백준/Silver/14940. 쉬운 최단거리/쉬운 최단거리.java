import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS
 * 방문하지 않은 -1을 어떻게 처리해야할까?
 *
 *
 * */
public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        int startR = -1;
        int startC = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startR = i;
                    startC = j;
                }
                if (map[i][j] == 1) {
                    dp[i][j] = -1;
                }
            }
        }

        bfs(startR, startC);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int startR, int startC) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();

        visited[startR][startC] = true;
        queue.add(new Point(startR, startC));
        dp[startR][startC] = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
                dp[nr][nc] = dp[now.r][now.c] + 1;

            }
        }

    }
}
