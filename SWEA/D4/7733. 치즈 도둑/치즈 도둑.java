import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, cnt, max;
	static boolean[][] visit;
	static int[][] arr;
	static int maxNum = Integer.MIN_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			// dfs를 덜 돌기 위해 배열 안에 들어가 있는 최대값을 구해준다.
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxNum = Math.max(maxNum, arr[i][j]);
				}
			}

			max = 0;

			// 최대값까지만 돌면서
			for (int cheese = 0; cheese < maxNum; cheese++) {
				cnt = 0;
				visit = new boolean[N][N];

				// 유통기간이 지난 치즈를 0으로 만든다.(cheese for문이 돌면서 0이 축적된다. 그러므로 이미 지난 치즈 값들을 별도로 안적어도
				// 된다)
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][j] == cheese) {
							arr[i][j] = 0;
						}
					}
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visit[i][j] && arr[i][j] != 0) {
							dfs(i, j);
							cnt++;
						}
					}
				}
				max = Math.max(max, cnt);
			} // cheese for문
			sb.append("#" + tc + " " + max).append("\n");
		} // tc for문
		System.out.println(sb);
	}

	public static void dfs(int r, int c) {
		visit[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || arr[nr][nc] == 0) {
				continue;
			}
			dfs(nr, nc);

		}
	}
}