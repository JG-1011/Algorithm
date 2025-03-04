import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, year;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

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

        year = 0;
        while (true) {
            int icebergs = icebergCnt();
            if (icebergs >= 2) break;
            if (icebergs == 0){ // 문제에 조건이 써있음
                year = 0;
                break;
            }
            year++;
            melt();
        }

        System.out.println(year);
    }

    private static int icebergCnt() {
        visited = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    if (cnt > 0) return cnt + 1; // 두번쨰 빙산이 나오면 바로 리턴해서 최적화
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        return cnt;
    }

    private static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();

        visited[r][c] = true;
        queue.add(new Point(r, c));

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }
    }

    private static void melt() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    int zeroCnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0) continue;

                        zeroCnt++;
                    }

                    temp[i][j] = Math.max(0, map[i][j] - zeroCnt);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(temp[i], 0, map[i], 0, M);
        }
    }
}