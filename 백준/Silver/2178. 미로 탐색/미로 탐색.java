import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 미로에서 1은 이동가능, 0은 이동 불가
 * (1,1)에서 출발해서 (N,M)으로 도착할 때 최소로 움직이는 칸 수를 구하시오
 *
 * 풀이
 * 거리배열을 만들어서 적어서 이동
 */
public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    static int[][] map, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int startR, int startC) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();

        visited[startR][startC] = true;
        queue.add(new Point(startR, startC));
        dist[startR][startC] = 1;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
                dist[nr][nc] = dist[now.r][now.c] + 1;
            }
        }

        return dist[N - 1][M - 1];
    }
}