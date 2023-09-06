import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 원래는 Queue를 사용했으나, back 기능을 넣기 위해 LinkedList 변경해서 peekLast를 이용했다.(마지막 원소를 가져와야
		// 하므로)
		LinkedList<Integer> queue = new LinkedList<>();

		int N = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				queue.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (queue.size() > 0) {
					sb.append(queue.poll()).append("\n");
					break;
				}
				sb.append(-1).append("\n");
				break;
			case "size":
				sb.append(queue.size()).append("\n");
				break;
			case "empty":
				if (queue.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case "front":
				if (!queue.isEmpty()) {
					sb.append(queue.peek()).append("\n");
					break;
				}
				sb.append(-1).append("\n");
				break;
			case "back":
				if (!queue.isEmpty()) {
					sb.append(queue.peekLast()).append("\n");
					break;
				}
				sb.append(-1).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
}