import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 진우의 달 여행..
 * NxM 행렬
 * 각 원소의 값은 소모되는 연료의 양
 *
 * 지구 > 달로 가는 경우
 * 전에 움직인 방향으로 우주선은 이동불가 >> 연속으로 같은 방향 못움직임
 * 최대한 아끼며 dfs
 *
 * */

public class Main {
    static int[] dr = {1, 1, 1};
    static int[] dc = {-1, 0, 1};
    static int N, M, min;
    static int[][] map;

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

        min = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            dfs(0, i, map[0][i], -1, 0);
        }

        System.out.println(min);
    }

    private static void dfs(int nowR, int nowC, int sum, int preD, int depth) {
        if (depth == N - 1) {
            min = Math.min(sum, min);
        }

        for (int d = 0; d < 3; d++) {
            if (d == preD) continue;
            int nr = nowR + dr[d];
            int nc = nowC + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

            dfs(nr, nc, sum + map[nr][nc], d, depth + 1);
        }
    }
}
