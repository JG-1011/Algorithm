import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, wallCount, moveCount;

		public Point(int r, int c, int wallCount, int moveCount) {
			this.r = r;
			this.c = c;
			this.wallCount = wallCount;
			this.moveCount = moveCount;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 처리
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기

		map = new int[N][M]; // 지도 정보를 저장하는 배열

		// 지도 정보 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0'; // 문자열을 숫자로 변환하여 저장
			}
		}

		// BFS 탐색 결과 출력
		System.out.println(bfs());
	}

	private static int bfs() {
		boolean[][][] visit = new boolean[N][M][2]; // 방문 여부와 벽 부순 횟수를 저장하는 배열
		Queue<Point> queue = new LinkedList<>(); // BFS를 위한 큐

		// 시작 지점 추가
		queue.add(new Point(0, 0, 0, 1)); // 시작점 (0,0), 벽 부순 횟수 0, 이동 횟수 1
		visit[0][0][0] = true; // 시작 지점 방문 표시

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Point p = queue.poll(); // 큐에서 하나의 지점을 꺼냅니다.

				// 도착 지점에 도달했을 때 이동 횟수를 반환
				if (p.r == N - 1 && p.c == M - 1) {
					return p.moveCount;
				}

				// 상하좌우로 이동
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];

					// 범위를 벗어나면 무시
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}

					// 만약 벽이 아니고, 방문한 적이 없다면
					if (map[nr][nc] == 0 && !visit[nr][nc][p.wallCount]) {
						queue.add(new Point(nr, nc, p.wallCount, p.moveCount + 1)); // 이동 횟수를 증가하고 큐에 추가
						visit[nr][nc][p.wallCount] = true; // 방문 표시
					}

					// 만약 벽이고, 벽을 부순 횟수가 1번 미만이고, 방문한 적이 없다면
					if (map[nr][nc] == 1 && p.wallCount < 1 && !visit[nr][nc][p.wallCount + 1]) {
						queue.add(new Point(nr, nc, p.wallCount + 1, p.moveCount + 1)); // 이동 횟수와 벽 부순 횟수를 증가하고 큐에 추가
						visit[nr][nc][p.wallCount + 1] = true; // 방문 표시
					}
				}
			}
		}
		return -1; // 목적지까지 도달하지 못한 경우 -1 반환
	}
}
