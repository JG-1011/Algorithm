import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 불을 먼저 보내고 지훈이를 보낸다.
 * 불과 벽은 통과하지 못한다.
 * 불이 여러개 일 수 있다.
 * */
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

    static char[][] map;
    static int[][] fireTime, jihoonTime;
    static int R, C;
    static Queue<Point> jihoonQueue = new LinkedList<>();
    static Queue<Point> fireQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireTime = new int[R][C];
        jihoonTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);

                fireTime[i][j] = -1;
                jihoonTime[i][j] = -1;

                if (map[i][j] == 'J') {
                    jihoonQueue.add(new Point(i, j));
                    jihoonTime[i][j] = 1;
                }

                if (map[i][j] == 'F') {
                    fireQueue.add(new Point(i, j));
                    fireTime[i][j] = 1;
                }
            }
        }

        fireMove();
        int ans = jihoonMove();

        System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
    }
    
    private static void fireMove() {
        while (!fireQueue.isEmpty()) {
            Point now = fireQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if(map[nr][nc] == '#' || fireTime[nr][nc] != -1) continue;

                fireQueue.add(new Point(nr, nc));
                fireTime[nr][nc] = fireTime[now.r][now.c] + 1;
            }
        }
    }
    
    private static int jihoonMove() {
        while (!jihoonQueue.isEmpty()) {
            Point now = jihoonQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    return jihoonTime[now.r][now.c];
                }

                if(map[nr][nc] == '#' || jihoonTime[nr][nc] != -1) continue;
                // 불이 번진 곳 보다 && 먼저 이동해야 함
                if (fireTime[nr][nc] != -1 && fireTime[nr][nc] <= jihoonTime[now.r][now.c] + 1) continue;

                jihoonQueue.add(new Point(nr, nc));
                jihoonTime[nr][nc] = jihoonTime[now.r][now.c] + 1;
            }
        }

        return -1;
    }
}