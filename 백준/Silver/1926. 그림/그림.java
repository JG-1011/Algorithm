import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 그림의 개수와 넓이가 가장 넓은 그림을 출력해라
 * 1로 연결된 것을 그림이라고 한다.
 *
 * 풀이
 * BFS
 * 1이면 bfs 들어가서 넓이를 가져와
 * 카운트는 세고 넓이는 맥스와 비교
 *
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
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pictureCnt = 0;
        int maxSize = 0;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    maxSize = Math.max(bfs(i, j), maxSize);
                    pictureCnt++;
                }
            }
        }

        System.out.println(pictureCnt);
        System.out.println(maxSize);
    }

    private static int bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        visited[r][c] = true;
        queue.add(new Point(r, c));

        int cnt = 0;
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            cnt++;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }

        return cnt;
    }
}