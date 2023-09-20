import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	static List<List<Integer>> graph;
	static int N, cnt, max; // N: 한 변의 길이, cnt: 나누어지는 공간의 수 카운트, max: 최대 공간의 수
	static boolean[][] visit;
	static int[][] arr;
	static int maxNum = Integer.MIN_VALUE; // 장마의 최대 양 체크 , minNum도 했었는데 아무 지역도 안 잠길수도 있어서 뺐다.
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		// 입력값 넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxNum = Math.max(maxNum, arr[i][j]);
			}
		}

		max = 0;

		for (int a = 0; a <= maxNum; a++) {
			cnt = 0; // 매번 돌때마다 초기화 해줘야 함
			visit = new boolean[N][N]; // 이것도 마찬가지로

			// 비의 양에 따라 일치하면 0으로 바꿔준다. for문 돌면서 점점 0이 많아지겠지~?
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == a) {
						arr[i][j] = 0;
					}
				}
			}

			// 완탐 돌면서 나누어진 공간을 찾아라
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && arr[i][j] != 0) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			max = Math.max(max, cnt);
		}

		System.out.println(max);
	}

	// bfs 외운 공식 대입	
	public static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(r, c));
		visit[r][c] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || arr[nr][nc] == 0 || visit[nr][nc]) {
					continue;
				}
				queue.offer(new Point(nr, nc));
				visit[nr][nc] = true;

			}

		}
	}
}