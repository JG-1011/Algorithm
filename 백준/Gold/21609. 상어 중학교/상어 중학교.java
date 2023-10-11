import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 1. 가장 큰 그룹을 찾자
// 2. 그룹 삭제
// 3. 중력작용 > 회전 > 중력작용
class Group {
	int r, c, size, rainbow;

	public Group(int r, int c, int size, int black) {
		this.r = r;
		this.c = c;
		this.size = size;
		this.rainbow = black;
	}

}

public class Main {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visit;

	static List<Group> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

		ans = 0;
		while (true) {
			visit = new boolean[N][N];
			// 가장 큰 그룹 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visit[i][j]) {
						findMaxGroup(i, j, map[i][j]);
					}
				}
			}

			if (list.isEmpty()) {
				break;
			}
			// 정렬
			Collections.sort(list, new Comparator<Group>() {
				@Override
				public int compare(Group o1, Group o2) {
					if (o1.size == o2.size) {
						if (o1.rainbow == o2.rainbow) {
							if (o1.r == o2.r) {
								return o2.c - o1.c;
							}
							return o2.r - o1.r;
						}
						return o2.rainbow - o1.rainbow;
					}
					return o2.size - o1.size;
				}
			});

			remove(map[list.get(0).r][list.get(0).c]);

			// 중력
			gravity();
			// 회전
			rotation();
			// 중력
			gravity();

		}
		System.out.println(ans);
	}

	private static void rotation() {
		// 깊은복사
		int[][] map2 = new int[N][N];

		// 회전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map2[i][j] = map[j][N - i - 1];
			}
		}

		// 다시 대입
		for (int i = 0; i < N; i++) {
			map[i] = map2[i].clone();
		}
	}

	private static void gravity() {
		for (int i = 0; i < N; i++) {
			int blank = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (map[j][i] == -2) {
					blank++;
				} else if (map[j][i] == -1) {
					blank = 0;
				} else {
					if (blank != 0) {
						map[j + blank][i] = map[j][i];
						map[j][i] = -2;
					}
				}
			}
		}

	}

	private static void remove(int num) {
		boolean[][] removeCheck = new boolean[N][N];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(list.get(0).r, list.get(0).c));
		removeCheck[list.get(0).r][list.get(0).c] = true;
		map[list.get(0).r][list.get(0).c] = -2;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !removeCheck[nr][nc]
						&& (map[nr][nc] == num || map[nr][nc] == 0)) {
					queue.add(new Point(nr, nc));
					removeCheck[nr][nc] = true;
					map[nr][nc] = -2;
				}
			}
		}
		if (list.get(0).size >= 2) {
			ans += list.get(0).size * list.get(0).size;
		}
		list.clear();
	}

	private static void findMaxGroup(int r, int c, int num) {
		Queue<Point> queue = new LinkedList<>();

		queue.add(new Point(r, c));
		visit[r][c] = true;

		int blank = 0;
		int size = 0;
		while (!queue.isEmpty()) {
			Point p = queue.poll();

			size++;

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visit[nr][nc]
						&& (map[nr][nc] == num || map[nr][nc] == 0)) {
					if (map[nr][nc] == 0) {
						blank++;
					}
					queue.add(new Point(nr, nc));
					visit[nr][nc] = true;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					visit[i][j] = false;
				}
			}
		}
		if (size < 2) {
			return;
		}
		list.add(new Group(r, c, size, blank));
	}

	public static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}