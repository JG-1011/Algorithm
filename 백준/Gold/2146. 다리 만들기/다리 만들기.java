import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static List<Point> list = new ArrayList<>();

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

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) list.add(new Point(i, j));
            }
        }

        // 섬 번호 매기기
        int islandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    island(i, j, islandNum++);
                }
            }
        }

        // 최단거리 구하기
        int ans = Integer.MAX_VALUE;
        for (Point point : list) {
            if (isSee(point)) { // 바다가 한면이라도 있는지 판단하기 위해서
                ans = Math.min(ans, bfs(point, map[point.r][point.c]));
            }
        }

        System.out.println(ans - 1);
    }

    private static int bfs(Point start, int islandNum) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point now = queue.poll();

                if (map[now.r][now.c] != islandNum && map[now.r][now.c] != 0) return dist;

                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    if (map[nr][nc] == islandNum || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                }

            }
            dist++;
        }

        return Integer.MAX_VALUE;
    }

    private static void island(int r, int c, int islandNum) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();

        visited[r][c] = true;
        queue.add(new Point(r, c));
        map[r][c] = islandNum;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] != 1 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new Point(nr, nc));
                map[nr][nc] = islandNum;
            }

        }
    }

    private static boolean isSee(Point start) {
        int r = start.r;
        int c = start.c;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] == 0) {
                return true;
            }
        }

        return false;
    }
}