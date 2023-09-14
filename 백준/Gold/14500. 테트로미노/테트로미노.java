import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, max;
	static int[][] arr;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 처음 들어갈 때 방문표시 안해줘도 되는 이유 : 더하는 값이 어차피 0이기 때문에 다른 곳에 갔다가 다시 (i,j) 위치에 와도 상관없다.
				dfs(i, j, 0, 0);
			}
		}
		System.out.println(max);

	}

	public static void dfs(int r, int c, int depth, int sum) {
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// continue를 이용하면 시간을 줄일 수 있다.
			if (!(nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc])) {
				continue;
			}

			if (depth == 2) {
				visit[nr][nc] = true;
				dfs(r, c, depth + 1, sum + arr[nr][nc]);
				visit[nr][nc] = false;
			}
			visit[nr][nc] = true;
			dfs(nr, nc, depth + 1, sum + arr[nr][nc]);
			visit[nr][nc] = false;
		}
	}
}
