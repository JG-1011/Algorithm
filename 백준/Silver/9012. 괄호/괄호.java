import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			Stack stack = new Stack();
			String str = br.readLine();

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '(') {
					stack.push(str.charAt(j));
				} else { // ')' 나왔을 때
					try {
						stack.pop();
					} catch (Exception e) {
						stack.push("fair");
						break;
					}
				}
			}
			if (stack.isEmpty()) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}
}