import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 1 이동가능
// 0 이동불가
// 1,1에서 출발
// N,M위치로 이동할 때 지나야 하는 최소의 칸수를 구해
// 다익스트라?

// cnt는 1부터 시작
public class Main {
	static int N, M, cnt;
	static int[][] map;
	static int[][] dist;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

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
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		dist = new int[N][M];
		dist[0][0] = 1;
		bfs(0, 0);
		System.out.println(dist[N - 1][M - 1]);
	}

	private static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];

		queue.add(new Point(r, c));
		visit[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (now.r == N && now.c == M) {
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nc < 0 | nr >= N || nc >= M)
					continue;
				if (visit[nr][nc] || map[nr][nc] == 0)
					continue;

				queue.add(new Point(nr, nc));
				dist[nr][nc] = dist[now.r][now.c] + 1;
				visit[nr][nc] = true;
			}
		}
	}
}