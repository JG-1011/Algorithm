import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;

		// N-X+1 만큼 반복하면 될듯
		for (int i = 0; i < N - X + 1; i++) {
			int j = i + (X - 1);

			if (i == 0) {
				for (int k = 0; k <= j; k++) {
					sum += arr[k];
				}
				max = sum;
				cnt = 1;
			} else {
				sum = sum - arr[i - 1] + arr[j];
				if (sum == max) {
					cnt++;
				}
				if (sum > max) {
					max = sum;
					cnt = 1;
				}
			}
		}

		if (max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(cnt);
		}

	}
}
