import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();

		int N = Integer.parseInt(br.readLine());

		// 카드 큐에 넣기
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		// 1. 맨 위 버린다.
		// 2. 다음 맨 위 카드를 맨 아래로 보낸다.
		// 3. 카드가 한장 남았는지 확인한다.
		while (queue.size() > 0) {
			if (queue.size() == 1) {
				break;
			}
			queue.poll();
			int tmp = queue.poll();
			queue.add(tmp);
		}
		System.out.println(queue.peek());
	}
}