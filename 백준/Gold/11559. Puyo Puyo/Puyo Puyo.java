import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static char[][] map;
	static int ans;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new char[12][6];
		ans = 0;

		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		while (true) {
			flag = false;

			// 터트리기
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						bfs(i, j, map[i][j]);
					}
				}
			}
			if (!flag)
				break;
			ans++;
			// 중력
			gravity();
		}

		System.out.println(ans);

	}

	private static void gravity() {
		for (int j = 0; j < 6; j++) {
			int cnt = 0;
			for (int i = 12 - 1; i >= 0; i--) {
				if (map[i][j] == '.') {
					cnt++;
				} else if (map[i][j] != '.' && cnt != 0) {
					map[i + cnt][j] = map[i][j];
					map[i][j] = '.';
				}
			}
		}
	}

	private static void bfs(int r, int c, char color) {
		boolean[][] visit = new boolean[12][6];
		Queue<Point> queue = new LinkedList<>();
		Queue<Point> boom = new LinkedList<>();

		queue.add(new Point(r, c));
		boom.add(new Point(r, c));
		visit[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6 || map[nr][nc] != color || visit[nr][nc]) {
					continue;
				}
				queue.add(new Point(nr, nc));
				boom.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
		if (boom.size() >= 4) {
			flag = true;
			while (!boom.isEmpty()) {
				Point tmp = boom.poll();
				map[tmp.r][tmp.c] = '.';
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}