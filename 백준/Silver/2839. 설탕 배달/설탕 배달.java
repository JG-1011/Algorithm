import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int tmp = N / 5;
		int min = 5001;

		for (int i = tmp; i >= 0; i--) {
			if ((N - 5 * i) % 3 == 0) {
				min = Math.min(min, i + (N - 5 * i) / 3);
			}
		}
		if (min == 5001) {
			System.out.println(-1);
		} else {
			System.out.println(min);

		}
	}
}