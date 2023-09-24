import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N, M, sum;
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visit;
	static List<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		list.add(new Point(N - 2, 0));
		list.add(new Point(N - 2, 1));
		list.add(new Point(N - 1, 0));
		list.add(new Point(N - 1, 1));

		for (int movement = 0; movement < M; movement++) {
			visit = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());

			// 모든 구름이 d(i) 방향으로 s(i)칸 이동 , 기준점 (1,4)
			move(d, s);
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(movement + " : " + list.get(i).r + " " + list.get(i).c);
//			}
			// 각 구름에서 비가 내려 바구니 물의 양이 1 증가
			rain();

			// 구름이 사라진다
			cloudRemove();
			// 물이 증가한 바구니에서 대각선 방향으로 거리가 1인 칸에 물이 들어가 있는 바구니가 있으면 바구니 수 만큼 증가시킨다. (경계 못넘어감)

			// 물의 양이 2이상인 모든 칸에 구름이 생김 > 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 구름이 사라진 칸이 아니어야 한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] >= 2 && !visit[i][j]) {
						map[i][j] -= 2;
						list.add(new Point(i, j));
					}
				}
			}
		}
		// M 번이동이 끝나면 바구니 물의 양의 합을 구해보자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}

		System.out.println(sum);
	}

	public static void move(int d, int s) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).r = (N + list.get(i).r + dr[d] * (s % N)) % N;
			list.get(i).c = (N + list.get(i).c + dc[d] * (s % N)) % N;
		}

		for (int i = 0; i < list.size(); i++) {
			visit[list.get(i).r][list.get(i).c] = true;
		}
	}

	public static void rain() {
		for (int i = 0; i < list.size(); i++) {
			map[list.get(i).r][list.get(i).c] += 1;
		}
		list.clear();
	}

	public static void cloudRemove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j]) {
					int cnt = 0;
					for (int d = 1; d <= 4; d++) {
						int nr = i + dr[2 * d - 1]; // 대각선 방향에 물이 있으면 +1
						int nc = j + dc[2 * d - 1];
						if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 0) {
							continue;
						}
						cnt++;
					}
					map[i][j] += cnt;
				}
			}
		}
	}
}