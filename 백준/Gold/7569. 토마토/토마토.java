import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 인접 > 6방향 탐색
 * 며칠이 지나야 모든 토마토가 다 익는가?
 * 토마토가 들어있지 않을 수도 있다.
 * M,N,H 가 주어진다.
 * 
 * 1:익은토마토, 0:안익은토마도, -1:토마토없음
 * 
 * 처음부터 모두 익어 있으면 0 , 다 익지 않으면 -1 출력
 * 
 * 입력
 * 가장 밑 상자 정보부터 주어진다..
 * 
 * 풀이
 * 익은 토마토들이 동시에 퍼져야 함
 * 그러므로 for문을 돌면서 1을 다 넣는다
 * 또 3차배열을 통해 위아래까지 확인해야함
 * 
 * 
 * 
 * 
 * 
 * */
public class Main {
	static class Point {
		int r, c, z;

		public Point(int r, int c, int z) {
			this.r = r;
			this.c = c;
			this.z = z;
		}

	}

	static int M, N, H, blank, blank2, ans;

	static int[][][] map;

	static boolean[][][] visit;

	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };

	static Queue<Point> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		queue = new LinkedList<>();
		map = new int[N][M][H];
		visit = new boolean[N][M][H];

		blank = 0;
		blank2 = 0;
		ans = 0;

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						queue.add(new Point(i, j, k));
						visit[i][j][k] = true;
					}
					if (map[i][j][k] == 0)
						blank++;
				}
			}
		}

		if (blank == 0) {
			System.out.println(0);
		} else {
			System.out.println(bfs());
		}

	}

	private static int bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();

			ans++;
			while (size-- > 0) {
				Point now = queue.poll();

				if (map[now.r][now.c][now.z] == 0) {
					blank2++;
				}

				for (int d = 0; d < 6; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					int nz = now.z + dz[d];
					if (nr < 0 || nc < 0 || nz < 0 || nr >= N || nc >= M || nz >= H)
						continue;
					if (visit[nr][nc][nz] || map[nr][nc][nz] == -1)
						continue;

					queue.add(new Point(nr, nc, nz));
					visit[nr][nc][nz] = true;
				}
			}
		}
		if (blank == blank2) {
			return ans - 1;
		} else {
			return -1;
		}
	}
}