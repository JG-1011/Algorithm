import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int p = 0;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String t = st.nextToken();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'x' || c == 'X') {
                    p = i;
                    break;
                }
            }

            char ans = t.charAt(p);
            sb.append(Character.toUpperCase(ans));
        }
        System.out.println(sb);
    }

}