import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// DP배열 선언
		int[][] D = new int[N + 1][N + 1];

		// DP배열 초기화
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				D[i][0] = 1;
				D[i][1] = i;
				D[i][i] = 1;
			}
		}

		// DP배열 나머지 점화식으로 채우기
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < N; j++) {
				D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
				D[i][j] %= 10007;
			}
		}

		System.out.println(D[N][K]);

	}
}