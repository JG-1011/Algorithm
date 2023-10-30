import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * N X M 행렬을 만들어야 한다. 시작은 0부터 해도 될 듯?
 * 적어도 2변이상이 접촉을 해야지 사라진다. -> 접촉횟수를 담아둘 클래스 만들자
 * 
 * 가장자리에는 치즈가 놓이지 않는다 + 밖과안을 구별해야 한다. BFS를 돌려서 밖에만 true처리해서 구별
 * 모두 녹는데 걸리는 시간
 * 
 * 치즈 : 1
 * 빈공간 : 0
 * 
 * 밖에 먼저 bfs돌리고 > true처리를 한다. > 완전탐색 하면서 치즈 bfs처리를 한다.
 * 이때 상하좌우 확인하면서 돌아야 해
 * 
 * 확인할 조건은?
 * 
 * 
 * 
 * */

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

	static int N, M, ans;
	static int[][] map;

	static boolean[][] visit;

	static Queue<Point> cheeses = new LinkedList<>();

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

		ans = 0;
		while (true) {

			intoutCheck(0, 0);

			if (search()) {
				break;
			}

			melt();

			ans++;
		}
		System.out.println(ans);
	}

	private static void melt() {
		while (!cheeses.isEmpty()) {
			Point now = cheeses.poll();
			map[now.r][now.c] = 0;
		}
	}

	private static boolean search() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					int cnt = 0;
					// 사방탐색
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						if (map[nr][nc] == 0 && visit[nr][nc]) {
							cnt++;
						}
					}
					if (cnt > 1) {
						cheeses.add(new Point(i, j));
					}
				}
			}
		}
		if (cheeses.size() > 0) {
			return false;
		}
		return true;
	}

	private static void intoutCheck(int r, int c) {
		visit = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c));
		visit[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				queue.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
	}
}
