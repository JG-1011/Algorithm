import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * - 문제
 * 6방향을 봐야 한다...
 * 
 * 
 * - 출력
 * 다 익게 되는 최소 일수를 구해야 함
 * 
 * N X M 그리고 H
 * 
 * 1 -> 익은 토마토
 * 0 -> 익지 않은 토마토
 * -1 -> 빈 공간
 * 
 * 시작부터 익어있으면 0 출력
 * 모두 익지 못하면 -1 출력
 * 
 * 
 * 
 * */
public class Main {
	static class Point {
		int r, c, h;

		public Point(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}

	}

	static int dr[] = { -1, 1, 0, 0, 0, 0 };
	static int dc[] = { 0, 0, -1, 1, 0, 0 };
	static int dh[] = { 0, 0, 0, 0, -1, 1 };

	static int M, N, H, cnt, blank;
	static int[][][] map;
	static Queue<Point> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		blank = 0;
		cnt = 0;

		map = new int[N][M][H];

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						queue.add(new Point(i, j, k));
					}
					if (map[i][j][k] == 0) {
						blank++;
					}
				}
			}
		}

		System.out.println(blank == 0 ? 0 : bfs());
	}

	public static int bfs() {
		boolean[][][] visit = new boolean[N][M][H];
		int blank2 = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Point now = queue.poll();

				for (int d = 0; d < 6; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					int nh = now.h + dh[d];
					if (nr < 0 || nc < 0 || nh < 0 || nr >= N || nc >= M || nh >= H || visit[nr][nc][nh]) {
						continue;
					}
					if (map[nr][nc][nh] == 0) {
						queue.add(new Point(nr, nc, nh));
						visit[nr][nc][nh] = true;
						map[nr][nc][nh] = 1;
						blank2++;
					}
				}

			}
			cnt++;
		}
		if (blank == blank2) {
			return cnt - 1;
		} else {
			return -1;
		}
	}
}
