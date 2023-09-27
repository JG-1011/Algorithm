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
	}

	static class Ice {
		int r, c, zeroCnt;

		public Ice(int r, int c, int zeroCnt) {
			this.r = r;
			this.c = c;
			this.zeroCnt = zeroCnt;
		}
	}

	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우 방향 이동을 위한 배열
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도의 행 수
		M = Integer.parseInt(st.nextToken()); // 지도의 열 수

		map = new int[N][M]; // 빙산의 높이 정보를 저장하는 2차원 배열

		// map 정보 채우기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int year = 0;
		while (true) {
			visit = new boolean[N][M];
			int cnt = countIsland(); // 빙산이 몇 개의 섬으로 나뉘어 있는지 세는 함수 호출

			if (cnt >= 2) { // 빙산이 2개 이상으로 나뉘면 종료
				break;
			} else if (cnt == 0) { // 빙산이 모두 녹아 없어지면 종료
				year = 0;
				break;
			}

			visit = new boolean[N][M];

			// 빙산 녹이기
			melt();

			year++; // 1년이 지남
		}

		System.out.println(year); // 결과 출력
	}

	public static void melt() {
		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					queue.offer(new Point(i, j)); // 빙산의 위치를 큐에 추가
					visit[i][j] = true;
				}
			}
		}

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int cnt = 0;

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0 || visit[nr][nc]) {
					continue;
				}
				cnt++;
			}
			if (map[p.r][p.c] - cnt > 0) {
				map[p.r][p.c] -= cnt; // 주변 바다의 개수만큼 빙산을 녹임
			} else {
				map[p.r][p.c] = 0; // 녹인 결과가 음수가 되지 않도록 처리
			}
		}
	}

	// 빙산이 몇 개의 섬으로 나뉘어 있는지 탐색하는 함수
	public static int countIsland() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && map[i][j] != 0) {
					visit[i][j] = true;
					dfs(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}

	// DFS를 사용하여 빙산이 연결된 부분을 찾는 함수
	public static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0 || visit[nr][nc]) {
				continue;
			}
			visit[nr][nc] = true;
			dfs(nr, nc);
		}
	}
}
