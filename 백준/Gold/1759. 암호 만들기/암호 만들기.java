import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] arr, ans;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ans = new char[L];
		arr = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);

		combi(0, 0);

		System.out.println(sb);
	}

	public static void combi(int start, int depth) {
		if (depth == L) {
			if (check()) {
				for (char a : ans) {
					sb.append(a);
				}
				sb.append("\n");
				return;
			}
			return;
		}

		for (int i = start; i < C; i++) {
			ans[depth] = arr[i];
			combi(i + 1, depth + 1);
		}
	}

	// 암호문 조건식을 작성하는게 포인트!!!
	public static boolean check() {
		int aeiou = 0;
		int other = 0;

		for (int i = 0; i < L; i++) {
			if (ans[i] == 'a' || ans[i] == 'e' || ans[i] == 'i' || ans[i] == 'o' || ans[i] == 'u') {
				aeiou++;
			} else {
				other++;
			}
		}

		if (aeiou >= 1 && other >= 2) {
			return true;
		}
		return false;
	}
}
