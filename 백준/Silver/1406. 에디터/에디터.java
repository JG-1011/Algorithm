import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기 문자열 입력
        String initialString = br.readLine();
        int commandCount = Integer.parseInt(br.readLine());

        // 커서 왼쪽과 오른쪽을 관리할 Deque
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        // 초기 문자열을 모두 왼쪽 Deque에 추가
        for (char c : initialString.toCharArray()) {
            left.addLast(c);
        }

        // 명령어 처리
        for (int i = 0; i < commandCount; i++) {
            String command = br.readLine();

            if (command.startsWith("L")) {
                // 왼쪽으로 커서 이동
                if (!left.isEmpty()) {
                    right.addFirst(left.removeLast());
                }
            } else if (command.startsWith("D")) {
                // 오른쪽으로 커서 이동
                if (!right.isEmpty()) {
                    left.addLast(right.removeFirst());
                }
            } else if (command.startsWith("B")) {
                // 커서 왼쪽 문자 삭제
                if (!left.isEmpty()) {
                    left.removeLast();
                }
            } else if (command.startsWith("P")) {
                // 커서 왼쪽에 문자 추가
                char toAdd = command.charAt(2);
                left.addLast(toAdd);
            }
        }

        // 결과 생성
        StringBuilder result = new StringBuilder();
        for (char c : left) {
            result.append(c);
        }
        for (char c : right) {
            result.append(c);
        }

        // 결과 출력
        System.out.println(result);
    }
}
