import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 꼭지점으로 사각형의 개수를 구하므로 map은 boolean형으로 만들어도 될 듯
// x,y가 100까지 범위를 알려줌 , x좌표y좌표 이므로 map[y][x]로 봐야함
// 격자 밖으로 벗어나지 않는다 > 범위 설정 필요 없을 듯


public class Main {

	static boolean[][] map;

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new boolean[101][101];

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			curve(x, y, d, g);
		}
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static void curve(int x, int y, int d, int g) {
		List<Integer> list = new ArrayList<>();
		list.add(d);

		for (int i = 0; i < g; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add((list.get(j) + 1) % 4);
			}
		}

		map[y][x] = true;
		for (int i = 0; i < list.size(); i++) {
			y += dr[list.get(i)];
			x += dc[list.get(i)];
			map[y][x] = true;
		}
	}
}
