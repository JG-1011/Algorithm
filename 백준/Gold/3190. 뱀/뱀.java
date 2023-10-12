import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Time {
	int t;
	char d;

	public Time(int t, char d) {
		this.t = t;
		this.d = d;
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

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static List<Time> timeList = new ArrayList<>();
	static Deque<Point> deque = new LinkedList<>();

	static boolean[][] map;
	static boolean[][] appleMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		map = new boolean[N + 1][N + 1];
		appleMap = new boolean[N + 1][N + 1];

		// 입력값 받기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int appleR = Integer.parseInt(st.nextToken());
			int appleC = Integer.parseInt(st.nextToken());
			appleMap[appleR][appleC] = true;
		}

		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			timeList.add(new Time(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}

		Collections.sort(timeList, new Comparator<Time>() {

			@Override
			public int compare(Time o1, Time o2) {
				return o1.t - o2.t;
			}
		});

		deque.add(new Point(1, 1));
		map[1][1] = true;
		int r = 1;
		int c = 1;
		int d = 1;
		int ans = 0;
		int i = 0;

		while (true) {
			// 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			// 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc]) {
				ans++;
				break;
			}
			deque.add(new Point(nr, nc));
			map[nr][nc] = true;
			ans++;

			// 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			if (appleMap[nr][nc]) {
				appleMap[nr][nc] = false;

				// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
			} else {
				Point p = deque.pollFirst();
				map[p.r][p.c] = false;
			}
			if (i < timeList.size()) {
				if (ans == timeList.get(i).t) {

					switch (timeList.get(i).d) {
					case 'L':
						d = (d + 3) % 4;
						break;
					case 'D':
						d = (d + 1) % 4;
						break;
					}
					i++;
				}
			}
			r = nr;
			c = nc;
		}

		System.out.println(ans);
	}
}