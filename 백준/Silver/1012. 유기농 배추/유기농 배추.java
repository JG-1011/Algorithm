import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, K, cnt;
	static boolean[][] visit;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			visit = new boolean[N][M];
			map = new int[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int tmp = Integer.parseInt(st.nextToken());
				int tmp2 = Integer.parseInt(st.nextToken());
				map[tmp2][tmp] = 1;
			}
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visit[i][j] && map[i][j] == 1) {
						visit[i][j] = true;
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);

		} // tc

	}

	public static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] == 0) {
				continue;
			}
			visit[nr][nc] = true;
			dfs(nr, nc);

		}
	}
}
