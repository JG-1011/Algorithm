import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 미세먼지 정보를 저장하는 클래스
class Dust {
	int r, c, w;

	public Dust(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}
}

// 공기청정기 위치를 저장하고 비교하는 클래스
class Cleaner implements Comparator<Cleaner> {
	int r, c;

	public Cleaner(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int compare(Cleaner o1, Cleaner o2) {
		return o1.r - o2.r;
	}
}

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static Cleaner[] cleaner;

	static int[][] map, map2;
	static int R, C, T;
	static List<Dust> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 받기
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행의 개수
		C = Integer.parseInt(st.nextToken()); // 열의 개수
		T = Integer.parseInt(st.nextToken()); // 시뮬레이션 시간

		map = new int[R + 1][C + 1]; // 미세먼지 농도를 저장하는 배열
		cleaner = new Cleaner[2]; // 공기청정기 위치를 저장하는 배열
		int idx = 0;
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleaner[idx++] = new Cleaner(i, j);
				}
			}
		}

		// T 시간 동안 미세먼지 확산 및 공기청정기 작동
		for (int i = 0; i < T; i++) {
			spread(); // 미세먼지 확산
			clean(); // 공기청정기 작동
		}

		// 남아 있는 미세먼지 양을 계산하고 출력
		System.out.println(sum());
	}

	// 미세먼지 농도의 합을 계산하는 함수
	public static int sum() {
		int sum = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == -1) {
					continue;
				}
				sum += map[i][j];
			}
		}
		return sum;
	}

	// 공기청정기 작동 함수
	public static void clean() {
		// 윗 공기청정기
		for (int i = cleaner[0].r - 1; i > 1; i--) { // 좌
			map[i][1] = map[i - 1][1];
		}
		for (int i = 1; i < C; i++) { // 상
			map[1][i] = map[1][i + 1];
		}
		for (int i = 1; i < cleaner[0].r; i++) { // 우
			map[i][C] = map[i + 1][C];
		}
		for (int i = C; i > 2; i--) { // 하
			map[cleaner[0].r][i] = map[cleaner[0].r][i - 1];
		}
		map[cleaner[0].r][2] = 0;

		// 아래 공기청정기
		for (int i = cleaner[1].r + 1; i < R; i++) { // 좌
			map[i][1] = map[i + 1][1];
		}
		for (int i = 1; i < C; i++) { // 하
			map[R][i] = map[R][i + 1];
		}
		for (int i = R; i > cleaner[1].r; i--) { // 우
			map[i][C] = map[i - 1][C];
		}
		for (int i = C; i > 1; i--) { // 상
			map[cleaner[1].r][i] = map[cleaner[1].r][i - 1];
		}
		map[cleaner[1].r][2] = 0;
	}

	// 미세먼지 확산 함수
	public static void spread() {
		// 깊은 복사
		map2 = new int[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map2[i][j] = map[i][j];
			}
		}

		// 탐색과 확산
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= 1 && nc >= 1 && nr <= R && nc <= C && map[nr][nc] != -1) {
							map2[nr][nc] += map[i][j] / 5;
							cnt++;
						}
					}
					map2[i][j] -= (map[i][j] / 5) * cnt;
				}
			}
		}

		// map 배열에 복사
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = map2[i][j];
			}
		}
	}
}
