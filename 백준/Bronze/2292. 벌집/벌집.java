import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		System.out.println(minRoom(N));
	}

	// 최소 개수 방 찾는 함수
	public static int minRoom(int N) {
		// N이 1일 때
		if (N == 1) {
			return 1;
		} else {
			int sum = 1;
			int min = 0; // 최소방의 수
			while (true) {
				sum = sum + 6 * min;
				if (N <= sum) {
					break;
				}
				min++;
			}
			return min + 1; // 시작과 끝 포함이므로 +1을 해준다
		}

	}
}