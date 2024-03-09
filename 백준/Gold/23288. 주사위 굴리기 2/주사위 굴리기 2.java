import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
문제는 쉬었으나.. 실수가 너무 많았다
특히 주사위를 돌렸을 때 오타가 너무 많았다
다음부터는 손으로 적어놔라 그리고 보면서 적어라
*/


public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int N, M, K, r, c, d, mapResult, ans;
    static int[][] map;

    static int[][] dice = new int[][]{
            {0, 2, 0},
            {4, 1, 3},
            {0, 5, 0},
            {0, 6, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        r = 0;
        c = 0;
        d = 1;
        ans = 0;
        for (int tc = 0; tc < K; tc++) {
            mapResult = 0;
            move();
            dice(d);
            bfs();

            ans += mapResult;
            if (dice[3][1] > map[r][c]) {
                d = (d + 1) % 4;
            } else if (dice[3][1] < map[r][c]) {
                d = (d + 3) % 4;
            }
        }

        System.out.println(ans);

    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new Point(r, c));
        visited[r][c] = true;

        int cnt = 1;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != map[r][c] || visited[nr][nc]) {
                    continue;
                }

                queue.add(new Point(nr, nc));
                visited[nr][nc] = true;
                cnt++;
            }
        }
        mapResult = map[r][c] * cnt;
    }

    private static void move() {
        int nr = r + dr[d];
        int nc = c + dc[d];

        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
            d = (d + 2) % 4;
            nr = r + dr[d];
            nc = c + dc[d];

            r = nr;
            c = nc;
            return;
        }
        r = nr;
        c = nc;

    }

    public static void dice(int dir) {

        int[][] backup = new int[4][3];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                backup[i][j] = dice[i][j];
            }
        }
        switch (dir) {
            case 0: {
                dice[0][1] = backup[1][1];
                dice[1][1] = backup[2][1];
                dice[2][1] = backup[3][1];
                dice[3][1] = backup[0][1];
                break;
            }
            case 1: {
                dice[1][0] = backup[3][1];
                dice[1][1] = backup[1][0];
                dice[1][2] = backup[1][1];
                dice[3][1] = backup[1][2];
                break;
            }
            case 2: {

                dice[1][1] = backup[0][1];
                dice[2][1] = backup[1][1];
                dice[3][1] = backup[2][1];
                dice[0][1] = backup[3][1];
                break;
            }
            case 3: {
                dice[1][0] = backup[1][1];
                dice[1][1] = backup[1][2];
                dice[1][2] = backup[3][1];
                dice[3][1] = backup[1][0];
                break;
            }
        }
    }
}