import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, count;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N]; // arr[몇번째 줄인지] = 몇번째 칸인지
		count = 0; // 몇번 가능한지
		dfs(0);

		System.out.println(count);
	}

	public static void dfs(int depth) {
		if (depth == N) { // N번째 퀸까지 놨을 때
			count++; // 카운트를 센다
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[depth] = i; // (depth,i)에 퀸을 놨을 때
			if (check(depth)) { // 퀸을 놔도 되는 자리인지 체크
				dfs(depth + 1); // 자리가 확인되면 다음 퀸 불러온다.
			}
		}
	}

	public static boolean check(int r) { // 이전 행들과 비교해보며 가능한지 판단
		for (int i = 0; i < r; i++) {

			if (arr[r] == arr[i]) { // 같은 행에 있다면 (열은 인덱스이기 때문에 중복될 일이 없다)
				return false;

			} else if (Math.abs(r - i) == Math.abs(arr[r] - arr[i])) { // 같은 대각선에 있다면
				return false;

			}

		} // for문

		return true;
	}
}