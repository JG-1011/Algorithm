import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		//입력 받을때 조금 헷갈렸다
		/*
		9
		4
		1 3 // 이부분만 두자리여서
		1 5
		3
		2
		5
		2
		2
		5
		*/
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				stack.push(Integer.parseInt(st.nextToken()));
			} else if (command == 2) {
				if (stack.isEmpty()) {
					sb.append(-1);
					sb.append("\n");
				} else {
					sb.append(stack.pop());
					sb.append("\n");
				}
			} else if (command == 3) {
				sb.append(stack.size());
				sb.append("\n");
			} else if (command == 4) {
				if (stack.isEmpty()) {
					sb.append(1);
					sb.append("\n");
				} else {
					sb.append(0);
					sb.append("\n");
				}
			} else if (command == 5) {
				if (!stack.isEmpty()) {
					sb.append(stack.peek());
					sb.append("\n");
				} else {
					sb.append(-1);
					sb.append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}