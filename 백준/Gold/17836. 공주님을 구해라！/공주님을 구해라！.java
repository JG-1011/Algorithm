import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 조건이 많을 때 되는 부분을 찾아라
// 예를 들어 여기서 되는 부분은
// 1. 검이 있을 때 도달한 것
// 2. 검이 없을 때 도달한 것
// 이 두가지를 만족만 시켜주면 된다. 그리고 나머지는 Fail 처리

// 나머지 복잡하게 조건을 달아줄 필요가 없다.

public class Main {

	static class Point {
		int r, c, time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M, T, ans, sword;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		T = Integer.parseInt(st.nextToken()); // 시간 제한

		map = new int[N][M]; // 원본 배열

		// 입력받는 부분
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		// 너비 우선 탐색
		bfs();

		System.out.println(ans <= T ? ans : "Fail");
	}

	public static void bfs() {
		boolean[][] visit = new boolean[N][M]; // 방문 배열
		Queue<Point> queue = new LinkedList<>(); // bfs를 위한 큐
		queue.add(new Point(0, 0, 0)); // 큐에 시작지점 추가
		visit[0][0] = true; // 시작 지점 방문 처리

		// bfs 실행문
		while (!queue.isEmpty()) {
			// 현재 지점
			Point now = queue.poll();

			// 검이 발견됐을 때 검에서 도착지점까지의 거리를 구한 다음 ans에 넣어준다.
			if (map[now.r][now.c] == 2) {
				sword = now.time + (N - 1 - now.r) + (M - 1 - now.c);
				ans = Math.min(ans, sword);
			}

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1 || visit[nr][nc]) {
					continue;
				}

				queue.add(new Point(nr, nc, now.time + 1));
				visit[nr][nc] = true;

				// 도착지점에 도달 했을 때 이미 들어가 있을 sword 갔을 때 거리와 비교를 하게 된다. ( 소드가 없을 때도 그냥 ans에 넣으면 되므로 가넝)
				if (nr == N - 1 && nc == M - 1) {
					ans = Math.min(ans, now.time + 1);
				}

			}
		}
	}
}
