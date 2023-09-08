import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://st-lab.tistory.com/194
class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// DP초기화
		int[][] DP = new int[29 + 1][29 + 1];
		for (int i = 0; i <= 29; i++) {
			DP[i][0] = 1;
			DP[i][i] = 1;
			DP[i][1] = i;
		}

		// DP입력값넣기
		for (int i = 2; i <= 29; i++) {
			for (int j = 1; j < i; j++) {
				DP[i][j] = DP[i - 1][j] + DP[i - 1][j - 1];
			}
		}

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			System.out.println(DP[M][N]);

		}
	}
}