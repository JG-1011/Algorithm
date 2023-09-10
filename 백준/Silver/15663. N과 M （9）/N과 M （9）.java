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
			if (!visit[i]) {
				if (before != sortArr[i]) {// 어차피 정렬되어 있으니 중복된 값이 나와도 뒤만 보면 된다
					visit[i] = true;
					arr[depth] = sortArr[i];
					before = sortArr[i];
					dfs(depth + 1);
					visit[i] = false;
				}
			}
		}
	}
}