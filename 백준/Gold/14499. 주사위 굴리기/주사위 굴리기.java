import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

N x M 지도가 존재
처음 주사위에는 모두 0이다

지도 숫자가 0이면 주사위에서 복사 되어 주사위가 0이 된다
지도 위에 있는 숫자가 주사위 바닥면에 복사 > 지도는 0
1 2 3 4
동서북남

020
413
050
060

동쪽 서쪽 북쪽 남쪽
020 020 010 060
641 136 453 423
050 050 060 010
030 040 020 050

*/
public class Main {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static int N, M, R, C, K;
    static int[][] map, dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //입력받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int order = Integer.parseInt(st.nextToken());

            //지도에서 좌표이동
            if (move(order)) {
                //주사위이동
                roll(order);

                if (map[R][C] == 0) {
                    map[R][C] = dice[3][1];
                } else {
                    dice[3][1] = map[R][C];
                    map[R][C] = 0;
                }

                sb.append(dice[1][1]).append("\n");
            }
        }
        System.out.println(sb);

    }

    private static boolean move(int d) {
        int nr = R + dr[d];
        int nc = C + dc[d];

        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
            return false;
        }

        R = nr;
        C = nc;

        return true;
    }

    private static void roll(int order) {
        int[][] backup = new int[4][3];
        if (order == 1) {
            backup[1][2] = dice[1][1];
            backup[0][1] = dice[0][1];
            backup[3][1] = dice[1][2];
            backup[1][1] = dice[1][0];
            backup[2][1] = dice[2][1];
            backup[1][0] = dice[3][1];
        } else if (order == 2) {
            backup[1][0] = dice[1][1];
            backup[0][1] = dice[0][1];
            backup[1][1] = dice[1][2];
            backup[3][1] = dice[1][0];
            backup[2][1] = dice[2][1];
            backup[1][2] = dice[3][1];
        } else if (order == 3) {
            backup[0][1] = dice[1][1];
            backup[3][1] = dice[0][1];
            backup[1][2] = dice[1][2];
            backup[1][0] = dice[1][0];
            backup[1][1] = dice[2][1];
            backup[2][1] = dice[3][1];
        } else {
            backup[2][1] = dice[1][1];
            backup[1][1] = dice[0][1];
            backup[1][2] = dice[1][2];
            backup[1][0] = dice[1][0];
            backup[3][1] = dice[2][1];
            backup[0][1] = dice[3][1];
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                dice[i][j] = backup[i][j];
            }
        }
    }
}