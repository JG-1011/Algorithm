import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visit;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M]; // 뽑은 수를 담아야 하기 때문에 M
		visit = new boolean[N]; // 전체 수 중에 사용한 수를 체크해야 하기 때문에 N

		dfs(0);
		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) { // depth 와 M이 같아지면
			for (int a : arr) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) { // 만약 visit[i]에 들어가 있는 숫자를 사용하지 않았다면
				visit[i] = true; // true(사용)으로 바꾸고
				arr[depth] = i + 1; // arr 안에 내가 사용한 숫자를 넣어준다.
				dfs(depth + 1); // 재귀를 통해 들어간다.( for문부터 여기까지 온 것을 반복실행)
				visit[i] = false; // 재귀 끝나면 돌아오면서 false로 다시 만들어주기
			}
		}

	}
}