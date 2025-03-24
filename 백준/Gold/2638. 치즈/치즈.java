import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * N x M 격자
 * 2변이 실내온도면 녹는다
 * 안쪽인지 밖쪽인지 어떻게 구별할까?
 * 밖에 bfs 돌리고 밖이면 true처리 하고 false면 안쪽인거지
 *
 * */
public class Main {
    static int N, M, ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] inOutMap;

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
        int cheeseCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheeseCnt++;
            }
        }

        ans = 0;
        while (cheeseCnt > 0) {
            ans++;
            inOutMap = new boolean[N][M];
            inOutBfs(); // true : 밖, false : 안

            List<Point> meltList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && isMelt(i, j)) {
                        meltList.add(new Point(i, j));
                    }
                }
            }

            for (Point p : meltList) {
                map[p.r][p.c] = 0;
                cheeseCnt--;
            }
        }

        System.out.println(ans);
    }

    private static void inOutBfs() {
        Queue<Point> queue = new LinkedList<>();

        inOutMap[0][0] = true;
        queue.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || inOutMap[nr][nc] || map[nr][nc] == 1) continue;

                inOutMap[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }
    }

    private static boolean isMelt(int r, int c) {
        int zeroCnt = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            if (map[nr][nc] == 1 || !inOutMap[nr][nc]) continue;

            zeroCnt++;
        }

        return zeroCnt >= 2;
    }
}