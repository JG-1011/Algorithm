import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Balloon {
	int key;
	int val;

	public Balloon(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Deque<Balloon> deque = new ArrayDeque<>();

		// 입력값 넣기
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			deque.add(new Balloon(i, Integer.parseInt(st.nextToken())));
		}

		while (!deque.isEmpty()) {
			if (deque.size() == 1) {
				sb.append(deque.poll().key + " ");
				break;
			}
			int num = deque.peekFirst().val;
			sb.append(deque.pollFirst().key + " ");
			if (num > 0) { // 양수면
				for (int i = 0; i < num - 1; i++) {
					Balloon tmp = deque.pollFirst();
					deque.addLast(tmp);
				}
			} else { // 음수면
				for (int i = 0; i < (num * -1); i++) {
					Balloon tmp = deque.pollLast();
					deque.addFirst(tmp);
				}
			}
		}
		System.out.println(sb);
	}
}