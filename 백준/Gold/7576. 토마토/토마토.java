import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, max; // 지도의 가로, 세로 크기와 최대 거리를 저장하는 변수
    static int[][] map, dp; // 지도 정보와 각 지점까지의 거리 정보를 저장하는 배열
    static boolean[][] visit; // 방문한 지점을 추적하는 배열
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 이동을 위한 배열
    static int[] dc = { 0, 0, -1, 1 }; // 상하좌우 이동을 위한 배열
    static boolean isOk; // 모든 곳이 연결되어 있는지 여부를 확인하는 변수
    static Queue<Point> queue = new LinkedList<>(); // 좌표를 저장하는 큐

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

        map = new int[M][N]; // 지도 정보를 저장하는 배열
        visit = new boolean[M][N]; // 지점 방문 여부를 저장하는 배열
        dp = new int[M][N]; // 각 지점까지의 거리를 저장하는 배열
        isOk = false; // 초기에는 연결되지 않았다고 가정
        max = 0; // 최대 거리 초기화

        // 지도 정보를 입력받습니다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    queue.add(new Point(i, j)); // 섬의 좌표를 큐에 추가
                }
            }
        }

        // bfs 메서드 호출
        bfs();

        // 방문하지 않은 0인 지점이 있다면, -1을 출력합니다.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    isOk = true;
                }
            }
        }

        if (isOk) {
            System.out.println(-1);
        } else {
            // 최대 거리를 출력합니다.
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, dp[i][j]);
                }
            }
            System.out.println(max);
        }
    }

    // bfs를 통해 각 지점까지의 거리를 계산하는 메서드
    public static void bfs() {
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            visit[p.r][p.c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nc >= 0 && nr < M && nc < N && map[nr][nc] == 0 && !visit[nr][nc]) {
                    queue.offer(new Point(nr, nc));
                    dp[nr][nc] = dp[p.r][p.c] + 1; // 현재까지의 거리에 1을 더합니다.
                    visit[nr][nc] = true;
                }
            }
        }
    }
}
