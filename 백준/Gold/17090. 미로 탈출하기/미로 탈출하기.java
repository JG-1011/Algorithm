import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 파악
 * NxM 배열이용
 * 문자 모양에 따라 이동해야 하는 방향이 별개 > 사방탐색 이용할 듯
 * 탈출 가능한 칸의 수
 * ㄴ 탈출 가능한 칸 >> 해당 칸에서 이동을 시작해서
 * bfs or dp이용
 * 
 * 
 * 
 * 조건
 * 
 * 
 * 
 * 고민해야 할 점
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
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M, ans;
	static char[][] map;
	static int[][] ok;

	static Queue<Point> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		ok = new int[N][M]; // 이게 내 지름길을 위한 배열 (1 : 통과, 0 : 검색, -1 : 실패)

		// 나눴고
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// 완전탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ok[i][j] == 0) {
					dfs(i, j, map[i][j]);
				} else if (ok[i][j] == 1) {
					ans++;
				} else {
					continue;
				}
			}
		}
		System.out.println(ans);

	}

	public static void dfs(int r, int c, char dd) {

		// 들어왔으면 방문췍 하고 큐에 넣어
		queue.add(new Point(r, c));

		// 후보군으로 2를 넣는다 이때는 이게 되는것인지 아닌것인지 모른다.
		ok[r][c] = 2;

		// 일단 사방탐색 필요
		int d = direction(dd);

		// 방향을 알아냈으니 이동을 헤애겠지?
		int nr = r + dr[d];
		int nc = c + dc[d];

		// 밖으로 벗어나면 성공
		if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
			while (!queue.isEmpty()) {
				Point now = queue.poll();
				ok[now.r][now.c] = 1;
			}
			ans++;
			return;
		}

		if (ok[nr][nc] == 1) {
			while (!queue.isEmpty()) {
				Point now = queue.poll();
				ok[now.r][now.c] = 1;
			}
			ans++;
			return;
		}

		// 들렸던 곳을 다시 들렸다는 것은 돌고 있다는 뜻
		if (ok[nr][nc] == 2) {
			while (!queue.isEmpty()) {
				Point now = queue.poll();
				ok[now.r][now.c] = -1;
			}
			return;
		}

		// 아무일 없으면 다음 dfs들어가자
		dfs(nr, nc, map[nr][nc]);
	}

	public static int direction(char c) {
		if (c == 'U') {
			return 0;
		} else if (c == 'D') {
			return 1;
		} else if (c == 'L') {
			return 2;
		} else {
			return 3;
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(ok[i][j] + " ");
			}
			System.out.println();
		}
	}
}
