import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class bag {
	int w, v;

	public bag(int w, int v) {
		this.w = w;
		this.v = v;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<bag> list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new bag(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		// 이거 get(i-1) 어떻게 처리해야 하냐....
		for (int i = 1; i < N + 1; i++) {
			for (int w = 0; w < K + 1; w++) {
				if (w >= list.get(i - 1).w) {
					map[i][w] = Math.max(map[i - 1][w], map[i - 1][w - list.get(i - 1).w] + list.get(i - 1).v);
				} else {
					map[i][w] = map[i - 1][w];
				}
			}
		}
		System.out.println(map[N][K]);

	}
}
