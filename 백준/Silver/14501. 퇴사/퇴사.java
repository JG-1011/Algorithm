import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N+1일 되는 날 퇴사함 최대한 많은 상담을 하고 싶음
 * 배열을 만들어서 진행해보자
 * 
 * 최대 수익을 만들어보자
 * 
 * 
 * 
 * 
 * */

public class Main {
	static int N, ans;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MIN_VALUE;
		dfs(1, 0);
		System.out.println(ans);
	}

	private static void dfs(int day, int sum) {
		if (day > N) {
			ans = Math.max(ans, sum);
			return;
		}

		if (day + arr[day][0] <= N+1) { // N이 10이라고 생각하면 10일 (1,10)이 값이라면 N까지로만 한다면 마지막 10을 더하지 못하고 반환하므로 N+1까지 포함해줘
			dfs(day + arr[day][0], sum + arr[day][1]);
		} else {
			dfs(day + arr[day][0], sum); // 넘어가면 날짜만 더해서 넘겨 왜냐하면 기저조건 맞춰주기 위해서
		}

		dfs(day + 1, sum); // 처음부터 끝까지 흝어주기 위해
	}
}
