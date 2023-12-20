import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static int[] arr, arr2;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			arr = new int[k];
			arr2 = new int[6];
			for (int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			combi(0, 0);

			System.out.println(sb);
		}
	}

	public static void combi(int start, int depth) {
		if (depth >= 6) {
			for (int a : arr2) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < k; i++) {
			arr2[depth] = arr[i];
			combi(i + 1, depth + 1);
		}
	}
}
