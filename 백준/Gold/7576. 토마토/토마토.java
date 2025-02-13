import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 토마토 박스 안에 안 익은 토마토가 있음(여러개 존재할 듯)
 * 안 익은 토마토는 익은 토마토 옆에 있으면 하루 지나 익게 됨(4방탐색)
 * 며칠이 지나야 다 익는지 최소 일자를 구해라
 * 모든 토마토가 익은 상태라면 0을 출력, 모두 익지 못하는 상황이면 -1
 *
 * 해결
 * BFS 해결하면 될 듯
 * 익지 않은 토마토가 있는지 확인해야 할듯 > 익지 않은 토마토 개수를 카운트 해서 나중에 비교
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

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M, zeroCnt;
    static int[][] map;
    static Queue<Point> queue = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        map = new int[N][M];
        zeroCnt = 0;

        // 1:익은, 0:익지않은, -1:빈공간
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zeroCnt++;
                } else if (map[i][j] == 1) {
                    visited[i][j] = true;
                    queue.add(new Point(i, j));
                }
            }
        }

        System.out.println(zeroCnt == 0 ? 0 : bfs());
    }

    private static int bfs() {
        int day = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Point now = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (visited[nr][nc] || map[nr][nc] == -1) continue;
                    
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                    zeroCnt--;
                    
                }
            }

            day++;
        }

        return (zeroCnt == 0) ? day - 1 : -1;
    }
}