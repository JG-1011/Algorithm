import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 
 * --입력
 * 테케
 * 체스판크기
 * 나이트가 현재 있는 곳
 * 나이트가 이동하려는 칸
 * 
 * bfs이용
 * (X,Y) 이거  r c랑 반대임
 * 
 * 
 * 
 * */
class Knight {
	int r, c;

	public Knight(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Knight [r=" + r + ", c=" + c + "]";
	}

}

public class Main {
	static int[] dr = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dc = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int N, X, Y, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			Knight k = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());

			cnt = 0;
			bfs(k.r,k.c);

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	public static void bfs(int r, int c) {
		boolean[][] visit = new boolean[N][N];
		Queue<Knight> queue = new LinkedList<>();

		queue.add(new Knight(r, c));
		visit[r][c] = true;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Knight now = queue.poll();

				if (now.r == X && now.c == Y) {
					return;
				}

				for (int d = 0; d < 8; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) {
						continue;
					}
					queue.add(new Knight(nr, nc));
					visit[nr][nc] = true;
				}
			}
			cnt++;
		}

	}
}
