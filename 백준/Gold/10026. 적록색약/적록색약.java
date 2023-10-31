import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * if(map[nr][nc] == 'G' || map[nr][nc] == 'R'){
 * queue.add
 * 
 * for(
 * 
 * 
 * */
public class Main {
	static class Green {
		int r, c;

		public Green(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static char[][] map;

	static Queue<Green> greens;

	static boolean[][] visit;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		greens = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'G') {
					greens.add(new Green(i, j));
				}
			}
		}

		visit = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					bfs(i, j, map[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt).append("\n");

		while (!greens.isEmpty()) {
			Green g = greens.poll();
			map[g.r][g.c] = 'R';
		}

		visit = new boolean[N][N];
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					bfs(i, j, map[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt);

		System.out.println(sb);
	}

	private static void bfs(int r, int c, char color) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c));
		visit[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] != color) {
					continue;
				}

				queue.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
	}
}
