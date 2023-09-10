import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sortArr;
	static StringBuilder sb;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sortArr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sortArr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sortArr);

		arr = new int[M]; // 뽑은 수를 담아야 하기 때문에 M
		visit = new boolean[N];

		dfs(0, 0);
		System.out.println(sb);
	}

	public static void dfs(int start, int depth) {
		if (depth == M) {
			for (int a : arr) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			arr[depth] = sortArr[i];
			dfs(i, depth + 1);
		}
	}
}