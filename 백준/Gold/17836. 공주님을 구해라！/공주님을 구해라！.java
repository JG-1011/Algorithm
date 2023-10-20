import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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

	static int N, M, T, ans, gram;
	static int[][] map;
	static boolean flag,sword;
	
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
		// 검을 가지고 들어온 시간
		ans = Integer.MAX_VALUE;
		// 너비 우선 탐색
		bfs();
		
		if(ans > T) {
			System.out.println("Fail");
		} else {
			System.out.println(ans);
		}
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

			// 검을 발견
			if (map[now.r][now.c] == 2) {
				gram = now.time + (N - 1 - now.r) + (M - 1 - now.c); // 검을 발견한 이후 도착지까지의 시간
				ans = Math.min(ans, gram);
			}

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1 || visit[nr][nc]) {
					continue;
				}

				if(nr == N-1 && nc == M-1) {
					ans = Math.min(now.time + 1, ans);
				}
				
				queue.add(new Point(nr, nc, now.time + 1));
				visit[nr][nc] = true;
			}
		}
	}
}
