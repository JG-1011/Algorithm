
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Main {

	static class Point {
		int r, c, wallCount, moveCount;

		public Point(int r, int c, int wallCount, int moveCount) {
			this.r = r;
			this.c = c;
			this.wallCount = wallCount;
			this.moveCount = moveCount;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", wallCount=" + wallCount + ", moveCount=" + moveCount + "]";
		}
	}

	static boolean[][][] visit;
	static int N, M, Hx, Hy, Ex, Ey;
	static int[][] map;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken());
		Hy = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs(Hx, Hy));
	}

	public static int bfs(int r, int c) {
		visit = new boolean[N + 1][M + 1][2];
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c, 0, 0));
		visit[r][c][0] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			if (p.r == Ex && p.c == Ey) {
				return p.moveCount;
			}

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr <= 0 || nc <= 0 || nr >= N + 1 || nc >= M + 1) {
					continue;
				}

				// 벽이 아니고, 방문하지 않은 곳일 때
				if (map[nr][nc] == 0 && !visit[nr][nc][p.wallCount]) {
					queue.add(new Point(nr, nc, p.wallCount, p.moveCount + 1));
					visit[nr][nc][p.wallCount] = true;
				}
				// 벽이고, 방문하지 않은 곳일 때
				if (map[nr][nc] == 1 && p.wallCount == 0 && !visit[nr][nc][p.wallCount + 1]) {
					queue.add(new Point(nr, nc, p.wallCount + 1, p.moveCount + 1));
					visit[nr][nc][p.wallCount + 1] = true;
				}

			}
		}
		return -1;
	}

	public static void print() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				System.out.print(visit[i][j][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				System.out.print(visit[i][j][1] + " ");
			}
			System.out.println();
		}
	}
}
