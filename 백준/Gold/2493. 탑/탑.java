import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>(); // {탑 높이, 탑 번호(1-based)}
        int[] result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 스택이 비어있지 않고, 현재 탑이 스택 top보다 크다면 pop
            while (!stack.isEmpty() && stack.peek()[0] < height) {
                stack.pop();
            }

            // 스택이 비어있지 않다면, 수신하는 탑이 존재
            if (!stack.isEmpty()) {
                result[i] = stack.peek()[1]; // 현재 탑의 수신탑 저장
            } else {
                result[i] = 0; // 수신 탑이 없으면 0
            }

            // 현재 탑을 스택에 push (탑 높이, 탑 번호)
            stack.push(new int[]{height, i + 1});
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
