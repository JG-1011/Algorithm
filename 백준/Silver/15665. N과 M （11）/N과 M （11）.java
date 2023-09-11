import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] num, arr;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	// 링크드해쉬셋 사용했는데 시간초과 나옴

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);

		dfs(0);
		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) {
			for (int a : arr) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}
		int before = 0;
		for (int i = 0; i < N; i++) {
			if (before != num[i]) {
				before = num[i];
				arr[depth] = num[i];
				dfs(depth + 1);
			}
		}
	}
}