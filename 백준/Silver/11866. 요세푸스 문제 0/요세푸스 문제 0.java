import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 큐문제인듯
		// 1.K번째 사람을 pop 한다
		// 2.똑같이 진행
		// 3.모두 사라질 때까지
		Queue<Integer> queue = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 큐에 숫자 넣기
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		sb.append("<");
		while (queue.size() > 1) { // 출력방식 <1, 2, 3, 4, 5, 6, 7>를 맞춰주기 위해 1개 남기고 끝낸다 그리고 마지막에 뽑을 때 ", "를 안넣으면 딱 맞다
			for (int i = 0; i < K - 1; i++) {
				int num = queue.poll();
				queue.add(num);
			}

			sb.append(queue.poll()).append(", ");
		}
		sb.append(queue.poll()).append(">");

		System.out.println(sb);
	}
}