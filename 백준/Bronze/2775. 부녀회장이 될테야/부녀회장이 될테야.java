import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[15][15];

		// 아파트 초기값 잡아주기
		for (int i = 0; i < 15; i++) {
			map[i][1] = 1;
			map[0][i] = i;
		}

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());

			for (int i = 1; i < 15; i++) {
				for (int j = 2; j < 15; j++) {
					map[i][j] = map[i][j - 1] + map[i - 1][j];
				}
			}

			System.out.println(map[k][n]);
		}
	}
}