import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 크기가 "양수"인 부분수열 중에서
 * N과M 방식으로 구한다음에 계산해보자
 * N의 범위가 20이니까 백트래킹 사용해야 함
 * 
 * 
 * */
public class Main {
	static int N, S, count;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		count = 0;

		combi(0, 0);

		System.out.println(S == 0 ? count - 1 : count);
	}

	public static void combi(int depth, int sum) {
		if (depth == N) {
			if (sum == S)
				count++;
			return;
		}

		combi(depth + 1, sum + arr[depth]);
		combi(depth + 1, sum);
	}

}
