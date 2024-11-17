import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 커서의 위치를 알고 있어야 함
 * 문자의 위치도 알고 있어야 할까?
 * abcㅇdㅇ
 * 커서 d
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Stack<String> frontStack = new Stack<>();
        Stack<String> backStack = new Stack<>();

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            frontStack.push(s);
        }

        int order = Integer.parseInt(br.readLine());
        for (int i = 0; i < order; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            if (a.equals("P")) {
                String b = st.nextToken();
                frontStack.push(b);
            } else if (a.equals("L") && !frontStack.isEmpty()) {
                String pop = frontStack.pop();
                backStack.push(pop);
            } else if (a.equals("D") && !backStack.isEmpty()) {
                String pop = backStack.pop();
                frontStack.push(pop);
            } else if (a.equals("B") && !frontStack.isEmpty()) {
                frontStack.pop();
            }
        }

        while (!frontStack.isEmpty()) {
            String pop = frontStack.pop();
            backStack.push(pop);
        }

        while (!backStack.isEmpty()) {
            String pop = backStack.pop();
            sb.append(pop);
        }

        System.out.println(sb);

    }
}
