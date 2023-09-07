import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		System.out.println(Factorial(N));
	}

	public static long Factorial(int N) {
		if (N == 1 || N == 0) {
			return 1;
		}
		return N * (Factorial(N - 1));
	}
}