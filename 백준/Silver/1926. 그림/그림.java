import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { -0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visit;
	static int N, M, cnt, area, maxArea;

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
		visit = new boolean[N][M];
		cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}

		System.out.println(cnt);
		System.out.println(maxArea);
	}

	public static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c));
		visit[r][c] = true;
		area = 1;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0 || visit[nr][nc]) {
					continue;
				}
				queue.add(new Point(nr, nc));
				visit[nr][nc] = true;
				area += 1;

			}
		}

		maxArea = Math.max(maxArea, area);
	}

}
