import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 완탐으로 풀 수 있는가??
 * dp로 풀 수 있을 거 같다.
 * 아니면 dfs로
 * 
 * 1일권,1달권,3달권 중에 선택을 하면서 12월까지 다 선택해 보는 경우를 보면 된다 > 완탐
 * 1일권 > 1일권 > 1일권 ...
 * 1달권 > 1일권 > 1일권 ...
 * 3달권 > 1달권 > ...
 * 
 * 
 * 
 * 
 * */
//public class Swea_1952_수영장 {
//	static int[] cost, month;
//	static int min;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
//
//		int T = Integer.parseInt(br.readLine());
//
//		for (int tc = 1; tc <= T; tc++) {
//
//			cost = new int[4];
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < 4; i++) {
//				cost[i] = Integer.parseInt(st.nextToken());
//			}
//
//			month = new int[13];
//			st = new StringTokenizer(br.readLine());
//			for (int i = 1; i <= 12; i++) {
//				month[i] = Integer.parseInt(st.nextToken());
//			}
//			min = Integer.MAX_VALUE;
//			dfs(1, 0);
//
//			sb.append("#" + tc + " " + min).append("\n");
//		}
//		System.out.println(sb);
//	}
//
//	private static void dfs(int depth, int sum) {
//		if (sum >= min)
//			return;
//		// depth == 13 으로하면 12월에서 3달권 선택했을때 오류 발생하니까 >로 바꿔야함
//		if (depth > 12) {
//			min = Math.min(min, Math.min(cost[3], sum));
//			return;
//		}
//
//		if (month[depth] == 0) {
//			dfs(depth + 1, sum);
//		} else {
//			// 1일권 선택했을떄
//			dfs(depth + 1, sum + cost[0] * month[depth]);
//			// 1달권 선택했을때
//			dfs(depth + 1, sum + cost[1]);
//			// 3달권 선택했을때
//			dfs(depth + 3, sum + cost[2]);
//
//		}
//
//	}
//}

// DP로 풀기
public class Solution {
	static int[] cost, month;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			cost = new int[4];
			month = new int[13];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= 12; i++) {
				month[i] = Math.min(month[i - 1] + month[i] * cost[0], month[i - 1] + cost[1]);

				if (i >= 3) {
					month[i] = Math.min(month[i], month[i - 3] + cost[2]);
				}

			}
			sb.append("#" + tc + " " + Math.min(month[12], cost[3])).append("\n");
		}
		System.out.println(sb);
	}
}