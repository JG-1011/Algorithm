import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[] money = new int[] { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int[] cnt = new int[money.length];
			int num = Integer.parseInt(br.readLine());

			for (int i = 0; i < money.length; i++) {
				if (num >= money[i]) {
					cnt[i] = num / money[i];
					num %= money[i] * cnt[i];
				}
			}

			sb.append("#" + tc).append("\n");
			for (int a : cnt) {
				sb.append(a + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}