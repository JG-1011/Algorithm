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
		Stack<Character> stack = new Stack<>();
		// 조건을 확실하게 해놔야 한다..
		// 이 문제는 입력 값을 어떻게 받을지가 키포인트
		// .이 나올때까지 계속 입력받기
		while (true) {
			stack.clear();
			String str = br.readLine();
			if (str.equals(".")) {
				break;
			}

			for (int i = 0; i < str.length(); i++) {
				// ( 와 [이 나왔을 때
				if (str.charAt(i) == '(' || str.charAt(i) == '[') {
					stack.push(str.charAt(i));
					// ) 가 나왔을 때
				} else if (str.charAt(i) == ')') {
					if (!stack.isEmpty()) {
						if (stack.peek() == '(') {
							stack.pop();
						} else {
							stack.push('x');
							break;
						}
					} else {
						stack.push('x');
						break;
					}
					// ] 가 나왔을 때
				} else if (str.charAt(i) == ']') {
					if (!stack.isEmpty()) {
						if (stack.peek() == '[') {
							stack.pop();
						} else {
							stack.push('x');
							break;
						}
					} else {
						stack.push('x');
						break;
					}
				}
			}

			if (stack.isEmpty()) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
		}

		System.out.println(sb);
	}
}